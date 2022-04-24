package com.example.animalshelter.repository;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.service.AnimalNotFoundException;

/**
 * Database operation for Animal entity.
 */
public interface AnimalRepository {

    boolean add(Animal animal);

    Animal delete(Long id);

    void update(Animal animal) throws AnimalNotFoundException;
}
