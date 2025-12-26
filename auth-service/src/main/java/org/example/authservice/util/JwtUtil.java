package org.example.authservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.authservice.service.CustomUserDetail;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtUtil {

    // 🔐 Must be at least 256 bits for HS256
    private static final String SECRET_KEY =
            "my-super-secure-secret-key-my-super-secure-secret-key";

    private static final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 15;      // 15 minutes
    private static final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24; // 24 hours

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /* ================= TOKEN GENERATION ================= */

    private String buildToken(String username, long expiration, String tokenType, String userId) {
        return Jwts.builder()
                .setSubject(username)
                .claim("userName", username)
                .claim("tokenType", tokenType)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    public String generateAccessToken(CustomUserDetail user) {
        return buildToken(
                user.getUsername(),
                ACCESS_TOKEN_EXPIRATION,
                "access",
                user.getId()
        );
    }

    public String generateRefreshToken(CustomUserDetail user) {
        return buildToken(
                user.getUsername(),
                REFRESH_TOKEN_EXPIRATION,
                "refresh",
                user.getId()
        );
    }

    /* ================= CLAIM EXTRACTION ================= */

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).get("userName", String.class);
    }

    public boolean isAccessToken(String token) {
        return "access".equals(
                extractAllClaims(token).get("tokenType", String.class)
        );
    }

    public boolean isRefreshToken(String token) {
        return "refresh".equals(
                extractAllClaims(token).get("tokenType", String.class)
        );
    }

    /* ================= VALIDATION ================= */

    public boolean validateToken(String token, CustomUserDetail user) {
        final String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }
}