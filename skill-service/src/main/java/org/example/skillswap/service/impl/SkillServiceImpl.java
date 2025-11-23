package org.example.skillswap.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.skillswap.dto.SkillRequest;
import org.example.skillswap.dto.SkillResponse;
import org.example.skillswap.model.Skill;
import org.example.skillswap.repository.SkillRepository;
import org.example.skillswap.service.SkillService;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;



    @Override
    public void addSkill(String userEmail, SkillRequest request) {
        Skill skill = new Skill();
        skill.setSkillId(UUID.randomUUID());
        skill.setUserEmail(userEmail);
        skill.setTitle(request.getTitle());
        skill.setDescription(request.getDescription());
        skill.setCategory(request.getCategory());
        skill.setSkillType(request.getSkillType());
        skillRepository.save(skill);
        log.info("New skill added by {}: {}", userEmail, request.getTitle());
    }

    @Override
    public List<SkillResponse> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(s -> new SkillResponse(s.getTitle(), s.getCategory(),s.getSkillType(),s.getUserEmail()))
                .collect(Collectors.toList());
    }


    @Override
    public List<SkillResponse> getUserSkillsById(UUID id) {
        return skillRepository.findById(id).stream()
                .map(s -> new SkillResponse(s.getTitle(), s.getCategory(),s.getSkillType(),s.getUserEmail()))
                .collect(Collectors.toList());
    }

    public Boolean updateSkill(UUID id, SkillRequest request) {
        if(id==null){
            return false;
        }
        Skill skill = new Skill();
        skill.setSkillId(id);
        skill.setTitle(request.getTitle());
        skill.setDescription(request.getDescription());
        skill.setCategory(request.getCategory());
        skill.setSkillType(request.getSkillType());
        skillRepository.save(skill);
        log.info("New skill updated by {}: {}", id, request.getTitle());
        return true;
    }

    @Override
    public Boolean deleteSkill(UUID id) {
        if(id == null){
            return false;
        }
        skillRepository.deleteById(id);
        return true;
    }

}
