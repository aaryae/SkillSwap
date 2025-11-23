package org.example.skillswap.model;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Skill {

    private UUID skillId;
    private String userEmail;

    private String title;
    private String description;
    private String category;
    private BigDecimal price;
    private String providerEmail;
    private LocalDateTime createdAt;

    private String skillType; // OFFER or REQUEST

    private LocalDateTime updatedAt;



}
