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
    AnimalDto add(Animal animal);

    /**
     * Remove Animal object from a database
     * @param id value used to identify the animal
     * @return deleted animal object
     * @throws AnimalNotFoundException if object cannot be found
     */
    Animal delete(Integer id) throws AnimalNotFoundException;
}
