package com.example.animalshelter.service;

import com.example.animalshelter.AnimalTestUtils;
import com.example.animalshelter.controller.AnimalDto;
import com.example.animalshelter.model.Animal;
import com.example.animalshelter.model.AnimalGender;
import com.example.animalshelter.model.AnimalHealthStatus;
import com.example.animalshelter.model.AnimalType;
import com.example.animalshelter.repository.AnimalRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnimalServiceImplTest extends AnimalTestUtils {

    private final AnimalRepository animalRepository = mock(AnimalRepository.class);
    private final AnimalService animalService = new AnimalServiceImpl(animalRepository);

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
}