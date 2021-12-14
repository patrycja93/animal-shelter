package com.example.animalshelter.controller;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.service.AnimalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private static final String SUCCESSFUL_RESPONSE = "The animal has been added successfully.";
    private static final String FAILED_RESPONSE = "An error occurred during adding animal.";

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public String addAnimal(@RequestBody Animal animal) {
        return animalService.add(animal) ? SUCCESSFUL_RESPONSE : FAILED_RESPONSE;
    }

    @GetMapping
    public List<Animal> all() {
        return animalService.findAll();
    }

    @GetMapping("/{id}")
    public Animal one(@PathVariable Long id) {
        return animalService.findOne(id);
    }
}
