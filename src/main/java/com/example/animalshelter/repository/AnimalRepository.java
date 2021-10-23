package com.example.animalshelter.repository;

import com.example.animalshelter.model.Animal;

import java.util.List;

/**
 * Database operation for Animal entity.
 */
public interface AnimalRepository {

    boolean add(Animal animal);

    List<Animal> findAll();
}
