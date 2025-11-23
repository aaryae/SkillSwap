package org.example.skillswap.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.skillswap.helper.StatusEnum;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class RequestModel {

//    request_id (UUID)
//    requester_email
//            provider_email
//    skill_id (UUID) → from Skill Service
//    status: PENDING / ACCEPTED / REJECTED / COMPLETED
//    message (optional)
//    created_at
//            updated_at

    private UUID requestId;
    private String requesterEmail;
    private String providerEmail;
    private UUID skillId;

    private String message;

    private StatusEnum status;

    private Timestamp createdAt;
    private Timestamp updatedAt;

}
