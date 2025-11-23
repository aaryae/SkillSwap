package org.example.skillswap.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.skillswap.dto.SkillRequest;
import org.example.skillswap.dto.SkillResponse;
import org.example.skillswap.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public ResponseEntity<String> addSkill(@Valid @RequestBody SkillRequest request, HttpServletRequest httpRequest) {
        String email = httpRequest.getHeader("X-User-email");
        skillService.addSkill(email, request);
        return ResponseEntity.ok("Skill added successfully by " + email);
    }

    @GetMapping
    public ResponseEntity<List<SkillResponse>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }

}
