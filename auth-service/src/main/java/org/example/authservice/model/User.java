package org.example.authservice.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.authservice.helper.Role;
import org.example.authservice.helper.UserStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @NonNull
    private  String id;

    @Email(message="invalid email format")
    @NotBlank(message = "email is required")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message="password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @Size(max = 255)
    private String bio;

    @Size(max = 255)
    private String skillsOffered;

    @Size(max = 255)
    private String skillWanted;

    @NonNull
    private Role role=Role.USER;

    private String profileImage;
    private String createdAt;
    private String updatedAt;
    private UserStatus status=UserStatus.ACTIVE;



}
