package org.example.userservice.controller;


import lombok.AllArgsConstructor;
import org.example.commonlibrary.dto.ApiResponse;
import org.example.userservice.model.Profile;
import org.example.userservice.service.impl.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {

    private final ProfileService profileService;


    @GetMapping("/profile-by-email")
    public ResponseEntity<ApiResponse<Optional<Profile>>> getProfileByEmail(String email) {
        return ResponseEntity.ok(new ApiResponse<>("Succesfully fetched data",true,profileService.getProfileByEmail(email)));
    }

    @GetMapping("/profile-by-id")
    public ResponseEntity<ApiResponse<Optional<Profile>>> getProfileById(UUID id) {
        return ResponseEntity.ok(new ApiResponse<>("Succesfully fetched data",true,profileService.getProfileById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Optional<Profile>>> getProfile() {
        return ResponseEntity.ok(new ApiResponse<>("Succesfully fetched data",true,profileService.getProfile()));
    }



}
