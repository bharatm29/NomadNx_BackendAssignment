package com.bharat.backendAssignment.models;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

@Data
@Schema(title = "FirebaseResponseToken", accessMode = AccessMode.READ_ONLY)
public class FirebaseResponseToken {
    @Schema(requiredMode = RequiredMode.REQUIRED, description = "id or access token retrieved from Firebase Auth", example = "user@email.com")
    private String idToken;
}
