package com.bharat.backendAssignment.controllers;

import com.bharat.backendAssignment.models.FirebaseResponseToken;
import com.bharat.backendAssignment.models.UserCreateDto;
import com.bharat.backendAssignment.models.UserLoginDto;
import com.bharat.backendAssignment.services.UserService;
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

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createUser(@Validated @RequestBody UserCreateDto userCreateDto) {
        userService.create(userCreateDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FirebaseResponseToken> login(@Validated @RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(userService.login(userLoginDto));
    }
}
