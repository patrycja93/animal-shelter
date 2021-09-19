package com.example.animalshelter.controller;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.service.AnimalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private static final String SUCCESSFUL_RESPONSE = "Animal added successfully.";
    private static final String FAILED_RESPONSE = "An Error occurred during adding animal.";

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public String addAnimal(@RequestBody Animal animal) {
        return animalService.add(animal) ? SUCCESSFUL_RESPONSE : FAILED_RESPONSE;
    }
}
