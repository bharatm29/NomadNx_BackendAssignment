package com.bharat.backendAssignment.controllers;

import com.bharat.backendAssignment.models.SecuredNamesDataDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/data")
public class DataController {
    @Operation(summary = "Get names data", description = "Secured endpoint which returns names data. Must be called with an access token")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully retrieved names data",
                    content = @Content(schema = @Schema(implementation = SecuredNamesDataDto.class)))
    })

    @GetMapping("/names")
    public SecuredNamesDataDto getData() {
        return new SecuredNamesDataDto(List.of("Dominic Welch",
                "Robert Murray",
                "Michelle Campbell",
                "Alan Skinner",
                "Sam Grant",
                "Jan Blake",
                "Katherine Payne",
                "Irene Burgess",
                "Liam Newman",
                "Olivia Burgess"));
    }
}