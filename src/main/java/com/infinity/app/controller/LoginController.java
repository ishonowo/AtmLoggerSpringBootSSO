package com.infinity.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.http.HttpStatus;
import com.infinity.app.config.JwtUtils;

import java.util.Map;

@RestController
@RequestMapping("/atm/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public LoginController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> loginRequest) {

    	try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.get("username"), loginRequest.get("password"))
            );

            String jwt = jwtUtils.generateJwtToken(authentication.getName());
            return ResponseEntity.ok(Map.of("token", jwt));

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
