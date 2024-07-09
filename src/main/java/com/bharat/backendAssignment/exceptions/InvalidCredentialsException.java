package com.bharat.backendAssignment.exceptions;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCredentialsException extends ResponseStatusException {
    public InvalidCredentialsException(@NotNull String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
