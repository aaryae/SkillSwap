package org.example.authservice.controller;

import lombok.AllArgsConstructor;
import org.example.authservice.dto.request.LoginRequest;
import org.example.authservice.dto.request.PasswordResetRequest;
import org.example.authservice.dto.request.RefreshTokenRequest;
import org.example.authservice.dto.request.RegisterRequest;
import org.example.authservice.service.AuthService;
import org.example.commonlibrary.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok(ApiResponse.success("User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> login(@RequestBody LoginRequest request) {
        Map<String, String> tokens = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success("Login successful", tokens));
    }

    @GetMapping("/refreshToken")
    public ResponseEntity<ApiResponse<Map<String, String>>> refreshToken(RefreshTokenRequest request) {
        Map<String, String> tokens = authService.refreshToken(request);
        return ResponseEntity.ok(ApiResponse.success("Refresh token successful", tokens));
    }

    @PostMapping("/request-reset")
    @Operation(summary = "Request password reset code")
    public ResponseEntity<ApiResponse<Boolean>> requestReset(@RequestBody LoginRequest loginRequest) {
        authService.sendResetCode(loginRequest.email());
        return ResponseEntity.ok(ApiResponse.success("Password reset code sent to your email.", true));
    }

    @PostMapping("/verify-reset")
    @Operation(summary = "Verify password reset code and reset password")
    public ResponseEntity<ApiResponse<Boolean>>verifyReset(@RequestBody PasswordResetRequest request) {
        authService.verifyAndResetPassword(request);
        return ResponseEntity.ok().body(new ApiResponse("Password reset successfully.", true));
    }
}