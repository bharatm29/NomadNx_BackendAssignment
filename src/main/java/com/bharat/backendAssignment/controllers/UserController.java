package com.bharat.backendAssignment.controllers;

import com.bharat.backendAssignment.models.ExceptionDto;
import com.bharat.backendAssignment.models.FirebaseResponseToken;
import com.bharat.backendAssignment.models.UserCreateDto;
import com.bharat.backendAssignment.models.UserLoginDto;
import com.bharat.backendAssignment.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Creates a user record", description = "Creates a user with provided email and password information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User record created successfully",
                    content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "409", description = "User account with provided email-id already exists",
                    content = @Content(schema = @Schema(implementation = ExceptionDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(schema = @Schema(implementation = ExceptionDto.class)))})
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createUser(@Validated @RequestBody UserCreateDto userCreateDto) {
        userService.create(userCreateDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Logins with credentials and returns a token", description = "Login the user with provided credentials and returns a firebase idToken which can be used to send further requests")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logged-in successfully and returned the token",
                    content = @Content(schema = @Schema(implementation = FirebaseResponseToken.class))),
            @ApiResponse(responseCode = "409", description = "User account with provided email-id already exists",
                    content = @Content(schema = @Schema(implementation = ExceptionDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(schema = @Schema(implementation = ExceptionDto.class)))})
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FirebaseResponseToken> login(@Validated @RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(userService.login(userLoginDto));
    }
}
