package org.example.skillswap.service;

import org.example.skillswap.dto.SkillRequest;
import org.example.skillswap.dto.SkillResponse;

import java.util.List;
import java.util.UUID;


public interface SkillService {

    void addSkill(String userEmail, SkillRequest request);
    List<SkillResponse> getAllSkills();
    List<SkillResponse> getUserSkillsById(UUID id);
     Boolean deleteSkill(UUID id);
}
