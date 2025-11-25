package org.example.skillswap.model;


import lombok.*;
import org.example.skillswap.helper.StatusEnum;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    private UUID requestId;
    private String requesterEmail;
    private String providerEmail;
    private UUID skillId;

    private String message;

    private StatusEnum status;

    private Timestamp createdAt;
    private Timestamp updatedAt;

}
