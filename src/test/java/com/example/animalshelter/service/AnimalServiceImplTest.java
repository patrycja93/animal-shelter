package com.example.animalshelter.service;

import com.example.animalshelter.AnimalTestUtils;
import com.example.animalshelter.controller.AnimalDto;
import com.example.animalshelter.model.Animal;
import com.example.animalshelter.model.AnimalGender;
import com.example.animalshelter.model.AnimalHealthStatus;
import com.example.animalshelter.model.AnimalType;
import com.example.animalshelter.repository.AnimalRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

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
}