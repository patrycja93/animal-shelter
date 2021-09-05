package com.example.animalshelter.service;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.repository.AnimalRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AnimalServiceImplTest {

    private AnimalRepository animalRepository = Mockito.mock(AnimalRepository.class);
    private Animal dummyAnimal;

    @Test
    public void shouldSaveAnimal() {
        dummyAnimal = new Animal();
        dummyAnimal.setId(123456);
        AnimalService animalService = new AnimalServiceImpl(animalRepository);
        Mockito.when(animalRepository.save(dummyAnimal)).thenReturn(true);

        boolean result = animalService.save(dummyAnimal);

        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldNotSaveAnimalWhenIdIsEmpty() {
        dummyAnimal = new Animal();
        AnimalService animalService = new AnimalServiceImpl(animalRepository);

        boolean result = animalService.save(dummyAnimal);

        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldNotSaveAnimalWhenAnimalIsNull() {
        AnimalService animalService = new AnimalServiceImpl(animalRepository);

        boolean result = animalService.save(dummyAnimal);

        Assertions.assertThat(result).isFalse();
    }
}