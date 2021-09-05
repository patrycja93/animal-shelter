package com.example.animalshelter.repository;

import com.example.animalshelter.model.Animal;

/**
 * Database operation for Animal entity.
 */
public interface AnimalRepository {

    boolean save(Animal animal);
}
