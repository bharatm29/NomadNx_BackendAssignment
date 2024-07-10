package com.bharat.backendAssignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TokenVerificationException extends ResponseStatusException {
    private static String DEFAULT_MESSAGE = "Authentication Failed: Token missing, invalid or expired";

    public TokenVerificationException() {
        super(HttpStatus.UNAUTHORIZED, DEFAULT_MESSAGE);
    }
}