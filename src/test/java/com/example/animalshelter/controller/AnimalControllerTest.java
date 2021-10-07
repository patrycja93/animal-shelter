package com.example.animalshelter.controller;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.model.AnimalGender;
import com.example.animalshelter.model.AnimalHealthStatus;
import com.example.animalshelter.model.AnimalType;
import com.example.animalshelter.service.AnimalService;
import com.example.animalshelter.service.DeleteAnimalException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;

class AnimalControllerTest {

    private final AnimalService animalService = Mockito.mock(AnimalService.class);

    public static final Animal DUMMY_ANIMAL = Animal.builder()
            .id(4321)
            .name("Axel")
            .age(3)
            .type(AnimalType.DOG)
            .gender(AnimalGender.MALE)
            .healthStatus(AnimalHealthStatus.HEALTHY)
            .build();

    @Test
    public void shouldAddAnAnimal() {
        AnimalController animalController = new AnimalController(animalService);
        Mockito.when(animalService.add(DUMMY_ANIMAL)).thenReturn(true);

        String result = animalController.addAnimal(DUMMY_ANIMAL);

        assertThat(result).isEqualTo("Request completed successfully");
    }

    @Test
    public void shouldNotAddAnAnimal() {
        AnimalController animalController = new AnimalController(animalService);
        Mockito.when(animalService.add(DUMMY_ANIMAL)).thenReturn(false);

        String result = animalController.addAnimal(DUMMY_ANIMAL);

        assertThat(result).isEqualTo("An error occurred");
    }

    @Test
    public void shouldDeleteAnAnimal() throws DeleteAnimalException {
        AnimalController animalController = new AnimalController(animalService);
        Mockito.when(animalService.delete(1)).thenReturn(true);

        String result = animalController.deleteAnimal(1);

        assertThat(result).isEqualTo("Request completed successfully");
    }

    @Test
    public void shouldNotDeleteAnAnimal() throws DeleteAnimalException {
        AnimalController animalController = new AnimalController(animalService);
        Mockito.when(animalService.delete(1)).thenReturn(false);

        String result = animalController.deleteAnimal(1);

        assertThat(result).isEqualTo("An error occurred");
    }
}
