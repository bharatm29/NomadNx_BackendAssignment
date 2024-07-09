package com.bharat.backendAssignment.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDto {
    private String description;
    private String status;
}
