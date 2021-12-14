package com.example.animalshelter.repository;

import com.example.animalshelter.model.Animal;

import java.util.List;
import java.util.Optional;

/**
 * Database operation for Animal entity.
 */
public interface AnimalRepository {

    boolean add(Animal animal);

    List<Animal> findAll();

    Optional<Animal> findOne(Long id);
}
