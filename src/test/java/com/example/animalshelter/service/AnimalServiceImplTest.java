package com.example.animalshelter.service;

import com.example.animalshelter.AnimalTestUtils;
import com.example.animalshelter.model.Animal;
import com.example.animalshelter.repository.AnimalRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AnimalServiceImplTest extends AnimalTestUtils {

    private final AnimalRepository animalRepository = mock(AnimalRepository.class);
    private final AnimalService animalService = new AnimalServiceImpl(animalRepository);

    @Test
    public void shouldAddAnimal() {
        when(animalRepository.save(DUMMY_ANIMAL)).thenReturn(DUMMY_ANIMAL);

        Animal result = animalService.add(DUMMY_ANIMAL);

        assertThat(result).isEqualTo(DUMMY_ANIMAL);
    }

    @Test
    public void shouldDeleteAnimal() {
        when(animalRepository.findById(ID)).thenReturn(Optional.ofNullable(DUMMY_ANIMAL));
        Animal result = animalService.delete(ID);

        assertThat(result).isEqualTo(DUMMY_ANIMAL);
        assertThat(result.getId()).isEqualTo(ID);
    }

    @Test
    public void shouldDeleteAnimalThrowExceptionWhenIdIsNotPresent() {
        when(animalRepository.findById(ID)).thenReturn(Optional.empty());

        assertThatExceptionOfType(AnimalNotFoundException.class)
                .isThrownBy(() -> animalService.delete(ID))
                .withMessage("Animal with id 1 doesn't exist in the database.");
    }
}