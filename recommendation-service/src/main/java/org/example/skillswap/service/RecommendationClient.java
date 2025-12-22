package org.example.skillswap.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.skillswap.dto.SkillWanted;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class RecommendationClient {

    private final RestClient restClient;

    public Optional<String> findSkillWanted(UUID id){

        if (id == null) {
            log.error("[SkillClient] id is null");
            return Optional.empty();
        }
        try{
           SkillWanted response= restClient.get()
                    .uri("https://localhost:8080/api/skills",id)
                    .retrieve()
                    .body(SkillWanted.class);
            if (response == null || response.skillWanted() == null) {
                log.warn("[SkillClient] Skill owner not found for skillId={}", id);
                return Optional.empty();
            }

            log.info("[SkillClient] Fetched skill owner for skillId={} → {}", id, response.skillWanted());
            return Optional.of(response.skillWanted());

        }
        catch(Exception e){
            log.error(e.getMessage());
            return Optional.empty();
        }
    }




}
