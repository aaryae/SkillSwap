package org.example.authservice.service;

import org.example.authservice.dto.request.LoginRequest;
import org.example.authservice.dto.request.PasswordResetRequest;
import org.example.authservice.dto.request.RefreshTokenRequest;
import org.example.authservice.dto.request.RegisterRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AuthService {
    void register(RegisterRequest request);

    Map<String, String> login(LoginRequest loginRequest);

    Map<String, String> refreshToken(RefreshTokenRequest request);

    void sendResetCode(String email);

    void verifyAndResetPassword(PasswordResetRequest request);
}

