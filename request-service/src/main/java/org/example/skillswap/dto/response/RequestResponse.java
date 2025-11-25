package org.example.skillswap.dto.response;

import java.util.UUID;

public record RequestResponse(
        UUID requestId,
        UUID skillId,
        String requesterEmail,
        String providerEmail,
        String message,
        String status
) {

}
