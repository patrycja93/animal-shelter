package com.example.animalshelter.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(int id) {
        super("Could not find the animal with id: " + id + ".");
    }
}
