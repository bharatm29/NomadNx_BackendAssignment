package com.bharat.backendAssignment.services;

import com.bharat.backendAssignment.configs.FirebaseConfigProperties;
import com.bharat.backendAssignment.exceptions.EmailAlreadyExistsException;
import com.bharat.backendAssignment.exceptions.InvalidCredentialsException;
import com.bharat.backendAssignment.models.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord.CreateRequest;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
@AllArgsConstructor
public class UserService {
    private final FirebaseConfigProperties firebaseConfigProperties;

    @SneakyThrows
    public void create(final UserCreateDto userCreateDto) {
        CreateRequest request = new CreateRequest()
                .setEmail(userCreateDto.getEmail())
                .setPassword(userCreateDto.getPassword())
                .setEmailVerified(true);

        try {
            FirebaseAuth.getInstance().createUser(request);
        } catch (FirebaseAuthException e) {
            if (e.getMessage().contains("EMAIL_EXISTS")) {
                throw new EmailAlreadyExistsException("Email " + userCreateDto.getEmail() + " is already taken");
            }
        }
    }

    @SneakyThrows
    public FirebaseResponseToken login(final UserLoginDto userLoginDto) {
        final FirebaseSignInRequestDto requestBody = FirebaseSignInRequestDto.builder()
                .email(userLoginDto.getEmail())
                .password(userLoginDto.getPassword())
                .returnSecureToken(Boolean.TRUE)
                .build();

        final FirebaseResponseToken response;
        try {
            response = RestClient
                    .create(firebaseConfigProperties.getWebUrl())
                    .post()
                    .uri(uriBuilder -> uriBuilder.queryParam("key", firebaseConfigProperties.getWebKey()).build())
                    .body(requestBody)
                    .retrieve()
                    .body(FirebaseResponseToken.class);
        } catch (HttpClientErrorException e) {
            if (e.getMessage().contains("INVALID_LOGIN_CREDENTIALS")) {
                throw new InvalidCredentialsException("Invalid Login Credentials");
            }
            throw e;
        }

        return response;
    }
}