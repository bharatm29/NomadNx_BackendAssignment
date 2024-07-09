package com.bharat.backendAssignment.exceptions;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailAlreadyExistsException extends ResponseStatusException {
    public EmailAlreadyExistsException (@NotBlank String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
