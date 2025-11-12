package org.example.skillswap.model;


import jdk.jfr.Timestamp;
import lombok.*;
import org.example.skillswap.helper.Type;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    private UUID serviceId;

    private String title;

    private String description;

    private String category;

    private Type type;

    private Timestamp createdAt;


}
