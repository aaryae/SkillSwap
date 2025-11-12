package org.example.skillswap.model;


import jdk.jfr.Timestamp;
import lombok.*;
import org.example.skillswap.helper.Type;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    private UUID profileId;
    private String title;
    private String description;
    private String category;
    private BigDecimal price;
    private String providerEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
