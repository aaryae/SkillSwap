package org.example.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateProfileRequest {
    @NotBlank
    @Email
    private String email;

    private String bio;

    private String skillsOffered;
    private String skillsWanted;
}
