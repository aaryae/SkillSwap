package org.example.skillswap.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SkillRequest {
    @NotBlank
    private String title;

    private String description;
    private String category;

    @NotBlank
    private String skillType;
}
