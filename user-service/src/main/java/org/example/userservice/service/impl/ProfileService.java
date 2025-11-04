package org.example.userservice.service.impl;

import org.example.userservice.model.Profile;

import java.util.Optional;

public interface ProfileService {

    public Optional<Profile> getProfile();
    public Optional<Profile> getProfileByEmail(String email);
    public Optional<Profile> getProfileById(String id);
}
