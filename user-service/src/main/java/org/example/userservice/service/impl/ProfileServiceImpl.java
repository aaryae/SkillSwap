package org.example.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.userservice.dto.CreateProfileRequest;
import org.example.userservice.model.Profile;
import org.example.userservice.repository.ProfileRepository;
import org.example.userservice.service.ProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public Optional<Profile> getProfile() {
        return profileRepository.findAllProfile();
    }

    @Override
    public Optional<Profile> getProfileByEmail(String email) {
        return profileRepository.findByEmail(email);
    }

    @Override
    public Optional<Profile> getProfileById(UUID id) {
        return profileRepository.findById(id);
    }

    @Override
    @Transactional
    public Profile createProfile(@Valid CreateProfileRequest request) {
        profileRepository.findByEmail(request.getEmail()).ifPresent(existing -> {
            log.warn("Attempt to create profile with email already in use: {}", request.getEmail());
            throw new RuntimeException("Profile with email already exists: " + request.getEmail());
        });

        UUID id = UUID.randomUUID();
        Profile profile = new Profile();
        profile.setId(id);
        profile.setEmail(request.getEmail());
        profile.setBio(request.getBio());
        profile.setSkillsOffered(request.getSkillsOffered());
        profile.setSkillsWanted(request.getSkillsWanted());

        profileRepository.save(profile);
        log.info("Created profile with id={} email={}", id, request.getEmail());


        return profile;
    }

    @Override
    public Optional<Profile> getSkillsWanted(UUID id) {
       return profileRepository.createProfile();
    }


}
