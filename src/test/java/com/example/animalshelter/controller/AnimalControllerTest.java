package com.example.animalshelter.controller;

import com.example.animalshelter.model.Animal;
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
        dummyAnimal.setGender("male");
        dummyAnimal.setStatus("healthy");
        dummyAnimal.setName("Alex");
        dummyAnimal.setType("dog");
    }

    @Test
    public void shouldSaveAnAnimal() {
        AnimalController animalController = new AnimalController(animalService);
        Mockito.when(animalService.save(dummyAnimal)).thenReturn(true);

        Animal result = animalController.saveAnimal(dummyAnimal);

        assertThat(result).isEqualTo(dummyAnimal);
    }

    @Test
    public void shouldNotSaveAnAnimal() {
        AnimalController animalController = new AnimalController(animalService);
        Mockito.when(animalService.save(dummyAnimal)).thenReturn(false);

        Animal result = animalController.saveAnimal(dummyAnimal);

        assertThat(result).isInstanceOf(Animal.class);
        assertThat(result.getId()).isEqualTo(0);
        assertThat(result.getAge()).isEqualTo(0);
        assertThat(result.getGender()).isNull();
        assertThat(result.getName()).isNull();
        assertThat(result.getStatus()).isNull();
        assertThat(result.getType()).isNull();
    }
}