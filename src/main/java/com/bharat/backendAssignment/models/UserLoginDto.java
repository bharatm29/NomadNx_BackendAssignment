package com.bharat.backendAssignment.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDto {
    @NotBlank(message = "Email should not be empty")
    @Email(message = "Email format should be valid")
    private String email;

    @NotBlank(message = "Password should not be empty")
    private String password;
}
