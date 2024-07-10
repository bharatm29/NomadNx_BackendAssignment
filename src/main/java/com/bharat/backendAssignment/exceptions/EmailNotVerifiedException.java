package com.bharat.backendAssignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailNotVerifiedException extends ResponseStatusException {
    public EmailNotVerifiedException() {
        super(HttpStatus.UNAUTHORIZED, "Email not verified");
    }
}
