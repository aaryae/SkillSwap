package org.example.authservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateProfileRequest {
    @NotBlank
    @Email
    private String email;

    private String bio;

    private String skillsOffered;
    private String skillsWanted;
}
