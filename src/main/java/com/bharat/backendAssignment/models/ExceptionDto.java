package com.bharat.backendAssignment.models;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(title = "Error", accessMode = AccessMode.READ_ONLY)
public class ExceptionDto {
    private String description;
    private String status;
}
