package org.example.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.authservice.dto.request.*;
import org.example.authservice.service.UserClient;
import org.example.commonlibrary.exception.CustomValidationException;
import org.example.commonlibrary.exception.DuplicateResourceException;
import org.example.commonlibrary.exception.UserNotFoundException;
import org.example.authservice.model.User;
import org.example.authservice.repository.UserRepository;
import org.example.authservice.service.AuthService;
import org.example.authservice.service.CustomUserDetail;
import org.example.authservice.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserClient userClient;



    @Override
    public void register(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.email());
        if (existingUser.isPresent()) {
            throw new DuplicateResourceException("User already exists with email " + request.email());
        }
        User user = User.builder()
                .username(request.email())
                .password(passwordEncoder.encode(request.password()))
                .email(request.email())
                .profileImage(request.profileImage())
                .build();

        userRepository.save(user);

        CreateProfileRequest profileRequest = new CreateProfileRequest();
        userClient.createUserProfile(profileRequest);

        log.info(" Sent request to User Service for profile creation: {}", user.getEmail());


    }

    @Override
    public Map<String, String> login(LoginRequest request) {
        User user= userRepository.findByEmail(request.email())
                .orElseThrow(()->new UserNotFoundException("Username doesn't exists","username",request.email()));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new UserNotFoundException("Password incorrect","username",request.email());
        }
        CustomUserDetail customUserDetail = new CustomUserDetail(user);

        String accessToken = jwtUtil.generateAccessToken(customUserDetail);
        String refreshToken = jwtUtil.generateRefreshToken(customUserDetail);

        Map<String, String> response = new HashMap<>();
        response.put("accessToken", accessToken);
        response.put("refreshToken", refreshToken);

        return response;
    }

    @Override
    public Map<String, String> refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.refreshToken();

        if (jwtUtil.isAccessToken(refreshToken)) {
            throw new CustomValidationException("Invalid refresh token: received access token instead.");
        }

        String email = jwtUtil.extractAllClaims(refreshToken).getSubject();


        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User", "email", email));

        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        String newAccessToken = jwtUtil.generateAccessToken(customUserDetail);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", newAccessToken);
        return tokens;
    }


    @Override
    public void sendResetCode(String email) {

    }

    @Override
    public void verifyAndResetPassword(PasswordResetRequest request) {

    }
}
