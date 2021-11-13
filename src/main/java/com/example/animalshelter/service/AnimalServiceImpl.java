package com.example.animalshelter.service;

import com.example.animalshelter.controller.AnimalDto;
import com.example.animalshelter.model.Animal;
import com.example.animalshelter.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final static String REPOSITORY_EXCEPTION_MSG = "Error during saving an animal in database";
    private final static String INVALID_ID_MSG = "Invalid animal id number.";
    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public AnimalDto add(Animal animal) {
        if (!animalRepository.add(animal)) {
            throw new AddAnimalException(REPOSITORY_EXCEPTION_MSG);
        }
        return new AnimalDto(animal.getId());
    }

    @Override
    public Animal delete(Integer id) throws AnimalNotFoundException {
        if (id == null || id <= 0) {
            throw new AnimalNotFoundException(INVALID_ID_MSG);
        }
        return animalRepository.delete(id);
    }
}
