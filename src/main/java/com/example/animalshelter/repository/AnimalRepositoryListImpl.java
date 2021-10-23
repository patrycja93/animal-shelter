package com.example.animalshelter.repository;

import com.example.animalshelter.model.Animal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimalRepositoryListImpl implements AnimalRepository {

    private final List<Animal> animals;

    public AnimalRepositoryListImpl(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public boolean add(Animal animal) {
        return animals.add(animal);
    }

    @Override
    public List<Animal> findAll() {
        return animals;
    }
}
