package com.bharat.backendAssignment.exceptions;

import com.bharat.backendAssignment.models.ExceptionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(TokenVerificationException.class)
    public ResponseEntity<ExceptionDto> handleTokenVerificationException(TokenVerificationException ex, WebRequest request) {
        log.error("Exception: {}", ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ExceptionDto.builder()
                        .description(ex.getMessage())
                        .status(HttpStatus.UNAUTHORIZED.toString())
                        .build()
        );
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleAllExceptions(Exception ex, WebRequest request) {
        log.error("Exception: {}", ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                ExceptionDto.builder()
                        .description("Some error occurred: " + ex.getMessage())
                        .status(HttpStatus.NOT_IMPLEMENTED.toString())
                        .build()
        );
    }
}
