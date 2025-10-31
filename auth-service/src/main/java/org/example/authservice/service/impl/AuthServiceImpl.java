package org.example.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.authservice.dto.request.LoginRequest;
import org.example.authservice.dto.request.PasswordResetRequest;
import org.example.authservice.dto.request.RefreshTokenRequest;
import org.example.authservice.dto.request.RegisterRequest;
import org.example.authservice.model.User;
import org.example.authservice.repository.UserRepository;
import org.example.authservice.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    @Override
    public void register(RegisterRequest request) {
        Optional<User> user = userRepository.findByEmail(request.email());
        if (user.isPresent()) {
          
        }

    }

    @Override
    public Map<String, String> login(LoginRequest loginRequest) {
        return Map.of();
    }

    @Override
    public Map<String, String> refreshToken(RefreshTokenRequest request) {
        return Map.of();
    }

    @Override
    public void sendResetCode(String email) {

    }

    @Override
    public void verifyAndResetPassword(PasswordResetRequest request) {

    }
}
