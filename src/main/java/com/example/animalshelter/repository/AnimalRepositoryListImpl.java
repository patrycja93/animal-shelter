package com.example.animalshelter.repository;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.service.DeleteAnimalException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimalRepositoryListImpl implements AnimalRepository {

    private final static String ANIMAL_NOT_FOUND_MSG = "Animal with provided id doesn't exist in the database.";
    private final List<Animal> animals = new ArrayList<>();

    @Override
    public boolean add(Animal animal) {
        return animals.add(animal);
    }

    @Override
    public boolean delete(Integer id) throws DeleteAnimalException {
        return animals.remove(getAnimalWithId(id));
    }

    // This method won't be needed when we move to Spring Data
    private Animal getAnimalWithId(Integer id) throws DeleteAnimalException {
        return animals.stream()
                .filter(animal -> animal.getId() == id)
                .findAny()
                .orElseThrow(() -> new DeleteAnimalException(ANIMAL_NOT_FOUND_MSG));
    }
}
