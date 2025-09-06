package com.example.book.util;

import java.util.Date;
import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // At least 32 characters for HS256
    private final Key SECRET_KEY = Keys.hmacShaKeyFor(
            "this-is-a-very-strong-secret-key-32chars!".getBytes()
    );

    // Generate token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract username
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Extract expiration
    public Date extractExpiration(String token) {
        return getClaims(token).getExpiration();
    }

    // Validate token
    public boolean validateToken(String token, String username) {
        try {
            return extractUsername(token).equals(username) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
