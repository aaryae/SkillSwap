package org.example.skillswap.service;

import org.example.skillswap.dto.request.CreateRequest;
import org.example.skillswap.dto.response.RequestResponse;

import java.util.List;
import java.util.UUID;

public interface RequestService {
    void createRequest(String requesterEmail, CreateRequest request);
    void updateStatus(UUID requestId, String status);
    List<RequestResponse> getMyRequests(String requesterEmail);
    List<RequestResponse> getRequestsForMe(String providerEmail);
}
