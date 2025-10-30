package org.example.authservice.service;

import io.jsonwebtoken.Claims;
import org.example.authservice.model.User;

public interface JwtService {
    String buildToken(String subject, long expirationTime, String tokenType, String id);
    String generateAccessToken(CustomUserDetail user);
    String generateRefreshToken(CustomUserDetail user);
    boolean isAccessToken(String token);
    boolean isRefreshToken(String token);
    Claims extractAllClaims(String token);
    String extractUsername(String token);

}
