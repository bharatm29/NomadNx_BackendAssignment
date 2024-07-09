package com.bharat.backendAssignment.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateDto {
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be of valid format")
    private String email;

    @NotBlank(message = "Email must not be blank")
    @Size(min = 6, message = "Password should be at-most 6 character long")
    private String password;
}