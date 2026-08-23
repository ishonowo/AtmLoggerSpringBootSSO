package com.infinity.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.http.HttpStatus;
import com.infinity.app.config.JwtUtils;
import javax.naming.directory.Attributes;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/atm/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final LdapTemplate ldapTemplate;

    public LoginController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, LdapTemplate ldapTemplate) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.ldapTemplate= ldapTemplate;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> loginRequest) {

    	try {
    		String username = loginRequest.get("username");

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username	, loginRequest.get("password"))
            );
            
         // Follow-up lookup: fetch the 'mail' attribute for the now-authenticated user.
            // Search filter matches your schema: cn={0} under ou=users,ou=system.
            AttributesMapper<String> mailMapper = (Attributes attrs) -> {
                Object mail = attrs.get("mail") != null ? attrs.get("mail").get() : null;
                return mail != null ? mail.toString() : null;
            };

            List<String> results = ldapTemplate.search(
                    "ou=users",
                    "(cn=" + username + ")",
                    mailMapper
            );
            
            String email = results.isEmpty() ? null : results.get(0);	

            String jwt = jwtUtils.generateJwtToken(authentication.getName());
            return ResponseEntity.ok(Map.of("token", jwt, "email",email));

        }  catch (AuthenticationException e) {
            e.printStackTrace();
            Throwable root = e;
            while (root.getCause() != null) {
                root = root.getCause();
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", e.getClass().getName() + ": " + e.getMessage()
                    + " | root cause: " + root.getClass().getName() + ": " + root.getMessage()));
        }
    }

}
