package com.bharat.backendAssignment.models;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(title = "UserLogin", accessMode = AccessMode.WRITE_ONLY)
public class UserLoginDto {
    @NotBlank(message = "Email should not be empty")
    @Email(message = "Email format should be valid")
    @Schema(requiredMode = RequiredMode.REQUIRED, description = "email-id of user", example = "user@email.com")
    private String email;

    @NotBlank(message = "Password should not be empty")
    @Schema(requiredMode = RequiredMode.REQUIRED, description = "password to enable user login")
    private String password;
}
