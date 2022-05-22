package com.example.animalshelter.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AnimalRestExceptionHandler {

    @ExceptionHandler(AnimalNotFoundException.class)
    private ResponseEntity<Object> handleAnimalNotFound(AnimalNotFoundException exception) {
        AnimalApiError animalApiError = new AnimalApiError(HttpStatus.NOT_FOUND);
        animalApiError.setMessage(exception.getMessage());
        return buildResponseEntity(animalApiError);
    }

    private ResponseEntity<Object> buildResponseEntity(AnimalApiError error) {
        return new ResponseEntity<>(error, error.getStatus());
    }
}
