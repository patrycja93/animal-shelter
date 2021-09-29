package com.example.animalshelter.controller;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.model.AnimalGender;
import com.example.animalshelter.model.AnimalHealthStatus;
import com.example.animalshelter.model.AnimalType;
import com.example.animalshelter.service.AnimalService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;

class AnimalControllerTest {

    private final AnimalService animalService = Mockito.mock(AnimalService.class);
    private static Animal dummyAnimal;

    @BeforeAll
    static void setUp() {
        dummyAnimal = new Animal();
        dummyAnimal.setId(4321);
        dummyAnimal.setAge(3);
        dummyAnimal.setGender(AnimalGender.MALE);
        dummyAnimal.setHealthStatus(AnimalHealthStatus.HEALTHY);
        dummyAnimal.setName("Alex");
        dummyAnimal.setType(AnimalType.DOG);
    }

    @Test
    public void shouldAddAnAnimal() {
        AnimalController animalController = new AnimalController(animalService);
        Mockito.when(animalService.add(dummyAnimal)).thenReturn(true);

        String result = animalController.addAnimal(dummyAnimal);

        assertThat(result).isEqualTo("The animal has been added successfully.");
    }

    @Test
    public void shouldNotAddAnAnimal() {
        AnimalController animalController = new AnimalController(animalService);
        Mockito.when(animalService.add(dummyAnimal)).thenReturn(false);

        String result = animalController.addAnimal(dummyAnimal);

        assertThat(result).isEqualTo("An error occurred during adding animal.");
    }
}