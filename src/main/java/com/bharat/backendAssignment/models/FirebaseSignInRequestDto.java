package com.bharat.backendAssignment.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FirebaseSignInRequestDto {
    private String email;
    private String password;
    private boolean returnSecureToken;
}
