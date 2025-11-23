package org.example.skillswap.dto;

import lombok.*;


@AllArgsConstructor
@Builder
@Getter
@Setter
public class SkillResponse {
    private  String title;
    private  String category;
    private  String skillType;
    private  String userEmail;
}
