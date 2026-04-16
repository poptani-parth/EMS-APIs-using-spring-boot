package com.EMS.Practice_Project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtParser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-ms}")
    private long jwtExpirationMs;

    // Create signing key
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // Generate JWT token
    public String generateToken(Authentication authentication, Set<String> roles) {

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .claim("roles", roles)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    // Extract username from token
    public String getUsernameFromToken(String token) {

        JwtParser parser = Jwts.parser()
                .verifyWith(getSigningKey())
                .build();

        Claims claims = parser
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    // Validate JWT token
    public boolean validateToken(String token) {

        try {

            JwtParser parser = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build();

            parser.parseSignedClaims(token);

            return true;

        } catch (Exception ex) {
            return false;
        }
    }
}