package com.bharat.backendAssignment.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/data")
public class DataController {
    @GetMapping("/names")
    public List<String> getData() {
        return List.of("Dominic Welch",
                "Robert Murray",
                "Michelle Campbell",
                "Alan Skinner",
                "Sam Grant",
                "Jan Blake",
                "Katherine Payne",
                "Irene Burgess",
                "Liam Newman",
                "Olivia Burgess");
    }
}
