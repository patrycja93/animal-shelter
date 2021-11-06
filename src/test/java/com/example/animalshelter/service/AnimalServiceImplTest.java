package com.example.animalshelter.service;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.model.AnimalGender;
import com.example.animalshelter.model.AnimalHealthStatus;
import com.example.animalshelter.model.AnimalType;
import com.example.animalshelter.repository.AnimalRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnimalServiceImplTest {

    public static final String INVALID_ANIMAL_ID_NUMBER = "Invalid animal id number.";
    private final AnimalRepository animalRepository = mock(AnimalRepository.class);
    private final AnimalService animalService = new AnimalServiceImpl(animalRepository);
    private static final int ID = 4321;

    public static final Animal DUMMY_ANIMAL = Animal.builder()
            .id(ID)
            .name("Axel")
            .age(3)
            .type(AnimalType.DOG)
            .gender(AnimalGender.MALE)
            .healthStatus(AnimalHealthStatus.HEALTHY)
            .build();

    @Test
    public void shouldAddAnimal() {
        when(animalRepository.add(DUMMY_ANIMAL)).thenReturn(true);

        Animal result = animalService.add(DUMMY_ANIMAL);

        assertThat(result).isEqualTo(DUMMY_ANIMAL);
    }

    @Test
    public void shouldThrowExceptionWhenDatabaseError() {
        when(animalRepository.add(DUMMY_ANIMAL)).thenReturn(false);

        assertThatExceptionOfType(AddAnimalException.class).isThrownBy(
                () -> animalService.add(DUMMY_ANIMAL)
        ).withMessage("Could not create the animal Error during saving an animal in database.");
    }

    @Test
    public void shouldDeleteAnimal() throws AnimalNotFoundException {
        when(animalRepository.delete(ID)).thenReturn(DUMMY_ANIMAL);

        Animal result = animalService.delete(ID);

        assertThat(result).isEqualTo(DUMMY_ANIMAL);
        assertThat(result.getId()).isEqualTo(ID);
    }

    @Test
    public void shouldThrowExceptionWhenIdIsZero() {
        assertThatExceptionOfType(AnimalNotFoundException.class).isThrownBy(
                () -> animalService.delete(0)
        ).withMessage(INVALID_ANIMAL_ID_NUMBER);
    }

    @Test
    public void shouldThrowExceptionWhenIdIsBelowZero() {
        assertThatExceptionOfType(AnimalNotFoundException.class).isThrownBy(
                () -> animalService.delete(-1)
        ).withMessage(INVALID_ANIMAL_ID_NUMBER);
    }
}