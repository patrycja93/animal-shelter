package com.example.animalshelter.controller;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.service.AnimalService;
import com.example.animalshelter.service.AnimalNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalCreatedResponse addAnimal(@RequestBody Animal animal) {
        Animal createdAnimal = animalService.add(animal);
        return new AnimalCreatedResponse(createdAnimal);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public AnimalDeletedResponse deleteAnimal(@PathVariable Integer id) throws AnimalNotFoundException {
        Animal deletedAnimal = animalService.delete(id);
        return new AnimalDeletedResponse(deletedAnimal);
    }
}
