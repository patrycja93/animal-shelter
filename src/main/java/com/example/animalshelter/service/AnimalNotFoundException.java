package com.example.animalshelter.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Throw this exception when delete action failed.
 * Like when animal id is not present id database.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AnimalNotFoundException extends Exception {

    public AnimalNotFoundException(String message) {
        super(message);
    }

    public AnimalNotFoundException() {
        super();
    }
}
