package com.bharat.backendAssignment.models;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(title = "UserCreation", accessMode = AccessMode.READ_ONLY)
public class UserCreateDto {
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be of valid format")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "email-id of user", example = "user@email.com")
    private String email;

    @NotBlank(message = "Email must not be blank")
    @Size(min = 6, message = "Password should be at-least 6 character long")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "password for the user creation. Must be at-least 6 characters long")
    private String password;
}