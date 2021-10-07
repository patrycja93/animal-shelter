package com.example.animalshelter.service;

import com.example.animalshelter.model.Animal;

/**
 * Store logic for all operations on an Animal entity.
 */
public interface AnimalService {

    boolean add(Animal animal);

    boolean delete(int id) throws DeleteAnimalException;
}
