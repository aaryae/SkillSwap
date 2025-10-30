package org.example.authservice.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.authservice.service.CustomUserDetail;
import org.example.authservice.service.JwtService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private int EXPIRATION_TIME = 1000 * 60 * 60 * 24;


    @Override
    public String buildToken(String subject, long expirationTime, String tokenType, String id) {
     return  Jwts.builder()
               .setSubject(subject)
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                 .claim("userName", subject)
               .claim("tokenType", tokenType)
               .claim("userId",id )
               .signWith(SignatureAlgorithm.HS256, "secret".getBytes())
                .compact();
    }

    @Override
    public String generateAccessToken(CustomUserDetail user) {
        return buildToken(user.getUsername(),EXPIRATION_TIME,"access",user.getId());

    }

    @Override
    public String generateRefreshToken(CustomUserDetail user) {
        return buildToken(user.getUsername(),EXPIRATION_TIME,"refresh",user.getId());
    }

    @Override
    public boolean isAccessToken(String token) {
        return "access".equals(extractAllClaims(token).get("tokenType", String.class));
    }

    @Override
    public boolean isRefreshToken(String token) {


        return "refresh".equals(extractAllClaims(token).get("tokenType", String.class));
    }

    @Override
    public Claims extractAllClaims(String token) {
        System.out.println(extractAllClaims(token));
        return extractAllClaims(token);
    }

    @Override
    public String extractUsername(String token) {
        return (extractAllClaims(token).get("userName", String.class));
    }





}


