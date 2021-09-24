package com.example.animalshelter.service;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.repository.AnimalRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;

class AnimalServiceImplTest {

    private final AnimalRepository animalRepository = Mockito.mock(AnimalRepository.class);
    private Animal dummyAnimal;

    @Test
    public void shouldAddAnimal() {
        dummyAnimal = new Animal();
        dummyAnimal.setId(123456);
        AnimalService animalService = new AnimalServiceImpl(animalRepository);
        Mockito.when(animalRepository.add(dummyAnimal)).thenReturn(true);

        boolean result = animalService.add(dummyAnimal);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldThrowExceptionWhenDatabaseError() {
        dummyAnimal = new Animal();
        dummyAnimal.setId(123456);

        AnimalService animalService = new AnimalServiceImpl(animalRepository);
        Mockito.when(animalRepository.add(dummyAnimal)).thenReturn(false);

        assertThatExceptionOfType(AddAnimalException.class).isThrownBy(
                () -> animalService.add(dummyAnimal)
        ).withMessage("Could not create the animal Error during saving an animal in database.");
    }
}