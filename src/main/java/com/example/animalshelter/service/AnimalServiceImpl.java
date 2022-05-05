package com.example.animalshelter.service;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public Animal add(Animal animal) {
       return animalRepository.save(animal);
    }

    @Override
    public Long delete(Long id) {
       Animal animalToDelete = animalRepository.findById(id)
           .orElseThrow(() -> new AnimalNotFoundException(id));
        animalRepository.delete(animalToDelete);
        return id;
    }
}
