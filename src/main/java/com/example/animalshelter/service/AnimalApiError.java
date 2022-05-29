package com.example.animalshelter.service;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AnimalApiError {
    private final HttpStatus status;
    private final String message;

}
