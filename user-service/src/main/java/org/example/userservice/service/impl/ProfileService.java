package org.example.userservice.service.impl;

import org.example.userservice.dto.CreateProfileRequest;
import org.example.userservice.model.Profile;

import java.util.Optional;
import java.util.UUID;

public interface ProfileService {

    public Optional<Profile> getProfile();
    public Optional<Profile> getProfileByEmail(String email);
    public Optional<Profile> getProfileById(UUID id);
    public Profile createProfile( CreateProfileRequest request);
    }
