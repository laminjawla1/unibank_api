package com.unibank.api.configurations.jwt;

import com.unibank.api.users.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Objects;

@Log4j2
@Component
public class JwtUtil {
    private final SecretKey jwtSecretKey;
    private final int jwtExpirationMs;

    public JwtUtil(@Value("${unibank.jwtSecretKey}") String jwtSecretKey, @Value("${unibank.jwtExpirationMs}") int jwtExpirationMs) {
        this.jwtSecretKey = Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
        this.jwtExpirationMs = jwtExpirationMs;
    }

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return Jwts.builder()
                .subject(Objects.requireNonNull(user).getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(jwtSecretKey, Jwts.SIG.HS512)
                .compact();
    }

    public boolean validateToken(String token) {
        if (token == null || token.isBlank()) {
            log.debug("Token is null or empty");
            return false;
        }

        try {
            Jwts.parser()
                    .verifyWith(jwtSecretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.warn("JWT token expired for token: {}", token.substring(0, Math.min(20, token.length())));
        } catch (SecurityException | MalformedJwtException | UnsupportedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error validating JWT: {}", e.getMessage(), e);
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(jwtSecretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
