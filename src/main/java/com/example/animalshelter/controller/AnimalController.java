package com.example.animalshelter.controller;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.service.AnimalService;
import com.example.animalshelter.service.DeleteAnimalException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private static final String SUCCESSFUL_RESPONSE = "Request completed successfully";
    private static final String FAILED_RESPONSE = "An error occurred";

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public String addAnimal(@RequestBody Animal animal) {
        return animalService.add(animal) ? SUCCESSFUL_RESPONSE : FAILED_RESPONSE;
    }

    @DeleteMapping("/{id}")
    public String deleteAnimal(@PathVariable Integer id) throws DeleteAnimalException {
        return animalService.delete(id) ? SUCCESSFUL_RESPONSE : FAILED_RESPONSE;
    }
}
