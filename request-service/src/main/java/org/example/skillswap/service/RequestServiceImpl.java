package org.example.skillswap.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.skillswap.dto.request.CreateRequest;
import org.example.skillswap.dto.response.RequestResponse;
import org.example.skillswap.model.Request;
import org.example.skillswap.repository.RequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.example.skillswap.helper.StatusEnum.PENDING;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;
    private final SkillClient skillClient;

    @Override
    @Transactional
    public void createRequest(String requesterEmail, CreateRequest request) {

        // 1. Validate input
        validateCreateRequest(request);

        // 2. Retrieve provider email from Skill Service
        String providerEmail = skillClient
                .getSkillOwner(request.skillId())
                .orElseThrow(() -> {
                    log.error("[RequestService] Unable to fetch provider for skillId={}", request.skillId());
                    return new IllegalStateException("Skill owner not found");
                });

        if (providerEmail.equals(requesterEmail)) {
            throw new IllegalArgumentException("You cannot request your own skill");
        }

        // 3. Build domain object
        Request req = buildRequest(requesterEmail, providerEmail, request);

        // 4. Persist
        repository.save(req);

        log.info("[RequestService] Request created: {} → {} for skillId={}",
                requesterEmail, providerEmail, request.skillId());
    }

    @Override
    @Transactional
    public void updateStatus(UUID requestId, String status) {

        if (!List.of("ACCEPTED", "REJECTED", "COMPLETED").contains(status)) {
            throw new IllegalArgumentException("Invalid status value: " + status);
        }

        int updated = repository.updateStatus(requestId, status);

        if (updated == 0) {
            log.warn("[RequestService] No request found with id={}", requestId);
//            throw new RequestNotFoundException("Request not found");
            throw new RuntimeException("No request found with id=" + requestId);
        }

        log.info("[RequestService] Status updated for id={} → {}", requestId, status);
    }

    @Override
    public List<RequestResponse> getMyRequests(String requesterEmail) {

        if (requesterEmail == null || requesterEmail.isBlank()) {
            throw new IllegalArgumentException("Requester email cannot be null or empty");
        }

        List<RequestResponse> results = repository.findByRequester(requesterEmail);

        if (results.isEmpty()) {
            log.info("[RequestService] No outgoing requests found for user={}", requesterEmail);
            return Collections.emptyList();
        }

        log.info("[RequestService] Found {} outgoing requests for user={}",
                results.size(), requesterEmail);

        return results;
    }

    @Override
    public List<RequestResponse> getRequestsForMe(String providerEmail) {

        if (providerEmail == null || providerEmail.isBlank()) {
            throw new IllegalArgumentException("Provider email cannot be null or empty");
        }

        List<RequestResponse> results = repository.findByProvider(providerEmail);

        if (results.isEmpty()) {
            log.info("[RequestService] No incoming requests found for provider={}", providerEmail);
            return Collections.emptyList();
        }

        log.info("[RequestService] Found {} incoming requests for provider={}",
                results.size(), providerEmail);

        return results;
    }




    private void validateCreateRequest(CreateRequest request) {
        if (request.skillId() == null) {
            throw new IllegalArgumentException("Skill ID cannot be null");
        }

        if (request.message() != null && request.message().length() > 500) {
            throw new IllegalArgumentException("Message exceeds limit (max 500 chars)");
        }
    }

    private Request buildRequest(String requester, String provider, CreateRequest dto) {
        Request req = new Request();
        req.setRequestId(UUID.randomUUID());
        req.setSkillId(dto.skillId());
        req.setRequesterEmail(requester);
        req.setProviderEmail(provider);
        req.setMessage(dto.message());
        req.setStatus(PENDING);
        req.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        req.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return req;
    }
}
