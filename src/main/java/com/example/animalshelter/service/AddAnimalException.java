package com.example.animalshelter.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AddAnimalException extends RuntimeException {

    public AddAnimalException(String string) {
        super("Could not create the animal " + string + ".");
    }
}
