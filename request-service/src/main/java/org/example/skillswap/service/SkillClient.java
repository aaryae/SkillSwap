package org.example.skillswap.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.skillswap.dto.response.SkillOwnerResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class SkillClient {

    private final RestClient restClient;

    public Optional<String> getSkillOwner(UUID skillId) {

        if (skillId == null) {
            log.error("[SkillClient] skillId is null");
            return Optional.empty();
        }

        try {
            SkillOwnerResponse response = restClient.get()
                    .uri("http://localhost:8080/api/skills/{skillId}", skillId)
                    .retrieve()
                    .body(SkillOwnerResponse.class);

            if (response == null || response.userEmail() == null) {
                log.warn("[SkillClient] Skill owner not found for skillId={}", skillId);
                return Optional.empty();
            }

            log.info("[SkillClient] Fetched skill owner for skillId={} → {}", skillId, response.userEmail());
            return Optional.of(response.userEmail());

        } catch (Exception ex) {
            log.error("[SkillClient] Failed to fetch skill owner for skillId={}. Reason: {}",
                    skillId, ex.getMessage());
            return Optional.empty();
        }
    }
}
