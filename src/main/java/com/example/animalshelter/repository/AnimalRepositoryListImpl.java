package com.example.animalshelter.repository;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.service.AnimalNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimalRepositoryListImpl implements AnimalRepository {

    private final List<Animal> animals = new ArrayList<>();

    @Override
    public boolean add(Animal animal) {
        return animals.add(animal);
    }

    @Override
    public Animal delete(Long id) {
         Animal animalToBeDeleted = getAnimalWithId(id);
         animals.remove(animalToBeDeleted);
         return animalToBeDeleted;
    }

    @Override
    public void update(Animal animal) throws AnimalNotFoundException {
        if (animals.remove(getAnimalWithId(animal.getId()))){
            animals.add(animal);
        }
    }

    // This method won't be needed when we move to Spring Data
    private Animal getAnimalWithId(Long id) {
        return animals.stream()
                .filter(animal -> animal.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new AnimalNotFoundException(id));
    }
}
