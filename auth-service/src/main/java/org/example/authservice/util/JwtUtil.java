package org.example.authservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.authservice.service.CustomUserDetail;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil{

    private int EXPIRATION_TIME = 1000 * 60 * 60 * 24;



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

    public String generateAccessToken(CustomUserDetail user) {
        return buildToken(user.getUsername(),EXPIRATION_TIME,"access",user.getId());

    }


    public String generateRefreshToken(CustomUserDetail user) {
        return buildToken(user.getUsername(),EXPIRATION_TIME,"refresh",user.getId());
    }

    public boolean isAccessToken(String token) {
        return "access".equals(extractAllClaims(token).get("tokenType", String.class));
    }


    public boolean isRefreshToken(String token) {


        return "refresh".equals(extractAllClaims(token).get("tokenType", String.class));
    }

    public Claims extractAllClaims(String token) {
        System.out.println(extractAllClaims(token));
        return extractAllClaims(token);
    }

    public String extractUsername(String token) {
        return (extractAllClaims(token).get("userName", String.class));
    }





}


