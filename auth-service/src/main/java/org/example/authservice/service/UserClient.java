package org.example.authservice.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.authservice.dto.request.CreateProfileRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
@AllArgsConstructor
public class UserClient {

    private final RestClient restClient;

    public UserClient() {
        this.restClient = RestClient.create();
    }

    public void createUserProfile(CreateProfileRequest request) {
        try {
            String response = restClient.post()
                    .uri("http://localhost:8082/api/users/create-profile")
                    .body(request)
                    .retrieve()
                    .body(String.class);

            log.info(" Profile created successfully for {} | Response: {}", request.getEmail(), response);
        } catch (Exception e) {
            log.error("Failed to create profile for {} | Reason: {}", request.getEmail(), e.getMessage());
        }
    }

}
