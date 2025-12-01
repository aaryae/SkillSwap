package org.example.skillswap.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.commonlibrary.dto.ApiResponse;
import org.example.skillswap.dto.request.CreateRequest;
import org.example.skillswap.dto.request.UpdateRequest;
import org.example.skillswap.dto.response.RequestResponse;
import org.example.skillswap.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createRequest(
            @RequestBody CreateRequest request,
            HttpServletRequest httpRequest) {

        String requester = httpRequest.getHeader("X-User-Email");
        requestService.createRequest(requester, request);
        return ResponseEntity.ok(ApiResponse.success("Successfully fetched data"));
    }

    @GetMapping("/sent")
    public ResponseEntity<List<RequestResponse>> sentRequests(HttpServletRequest req) {
        String email = req.getHeader("X-User-Email");
        return ResponseEntity.ok(requestService.getMyRequests(email));
    }

    @GetMapping("/received")
    public ResponseEntity<List<RequestResponse>> receivedRequests(HttpServletRequest req) {
        String email = req.getHeader("X-User-Email");
        return ResponseEntity.ok(requestService.getRequestsForMe(email));
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(
            @PathVariable UUID id,
            @RequestBody UpdateRequest request) {
        requestService.updateStatus(id, String.valueOf(request.status()));
        return ResponseEntity.ok("Status updated");
    }
}
