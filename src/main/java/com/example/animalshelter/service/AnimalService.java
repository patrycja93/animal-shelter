package com.example.animalshelter.service;

import com.example.animalshelter.controller.AnimalDto;
import com.example.animalshelter.model.Animal;

/**
 * Store logic for all operations for an Animal entity.
 */
public interface AnimalService {

    /**
     * Persist the Animal object into a database.
     * @param animal object to be saved
     * @return same animal if the add action is successful
     */
    Animal add(Animal animal);

    /**
     * Remove Animal object from a database
     * @param id value used to identify the animal
     * @return deleted animal object
     */
    Animal delete(Long id);

    /**
     * Replace the animal entity with another one
     * @param animal the new value for animal entity
     * @throws AnimalNotFoundException if animal cannot be found
     */
    void update(Animal animal) throws AnimalNotFoundException;
}
