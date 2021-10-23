package com.example.animalshelter.service;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final static String REPOSITORY_EXCEPTION_MSG = "Error during saving an animal in database";
    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public boolean add(Animal animal) {
        if (!animalRepository.add(animal)) {
            throw new AddAnimalException(REPOSITORY_EXCEPTION_MSG);
        }
        return true;
    }

    @Override
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }
}
