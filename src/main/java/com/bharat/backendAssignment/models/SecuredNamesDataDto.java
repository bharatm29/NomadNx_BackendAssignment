package com.bharat.backendAssignment.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(title = "Names Data", accessMode = Schema.AccessMode.READ_ONLY)
public class SecuredNamesDataDto {
    private final List<String> names;
}
