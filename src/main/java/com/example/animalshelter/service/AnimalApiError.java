package com.example.animalshelter.service;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AnimalApiError {
    private HttpStatus status;
    private String message;

    AnimalApiError(HttpStatus status) {
        this.status = status;
    }

    AnimalApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
