package com.example.animalshelter.service;

public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(Long id) {
        super("Could not find the animal " + id + ".");
    }
}
