package com.arrowfin.lms.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String username) {
        // Logic to build and sign the JWT
        return username;
    }

    public boolean validateToken(String token) {
        // Logic to parse and validate the JWT
        return false;
    }

    public String getUsernameFromToken(String token) {
        // Logic to extract username from token claims
        return token;
    }
}