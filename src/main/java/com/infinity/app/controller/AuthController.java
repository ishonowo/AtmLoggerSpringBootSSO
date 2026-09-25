package com.infinity.app.controller;

import com.infinity.app.config.JwtUtils;
import com.infinity.app.dto.LoginRequest;
import com.infinity.app.model.AppUser;
import com.infinity.app.service.AppUserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/atm/auth")
public class AuthController {

    private final AuthenticationManager ldapAuthenticationManager;
    private final AppUserService appUserService;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager ldapAuthenticationManager,
                           AppUserService appUserService,
                           JwtUtils jwtUtils) {
        this.ldapAuthenticationManager = ldapAuthenticationManager;
        this.appUserService = appUserService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        // 1. Authentication: ApacheDS confirms the credentials over LDAPS.
        //    This never sees the password again after this call - it isn't
        //    stored, logged, or passed anywhere else.
        try {
            ldapAuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCn(), request.getPassword())
            );
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Invalid username or password."));
        }

        // 2. Authorization: our own table decides what this verified person
        //    can do here. A valid LDAP bind alone is not enough.
        AppUser user = appUserService.findActiveByLdapCn(request.getCn())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "LDAP credentials are valid, but there is no active application account for this user."
            ));

        appUserService.recordLogin(user);

        // 3. Roles are stamped into the JWT at issue time, so every later
        //    request is authorized from the token alone.
        String token = jwtUtils.generateJwtToken(user.getEmail(), user.getRoleNames());

        return ResponseEntity.ok(Map.of(
            "token", token,
            "email", user.getEmail(),
            "fullName", user.getFullName(),
            "roles", user.getRoleNames()
        ));
    }
}
