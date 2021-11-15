package com.example.animalshelter.repository;

import com.example.animalshelter.model.Animal;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Animal> findOne(Long id) {
        return animals.stream().filter(animal -> Long.valueOf(animal.getId()).equals(id))
                .findFirst();
    }
}
