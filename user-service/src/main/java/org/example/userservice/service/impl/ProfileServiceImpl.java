package org.example.userservice.service.impl;

import lombok.AllArgsConstructor;
import org.example.userservice.model.Profile;
import org.example.userservice.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
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
    public Optional<Profile> getProfileById(String id) {
        return profileRepository.findById(id);
    }
}
