package com.example.animalshelter.controller;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.model.AnimalGender;
import com.example.animalshelter.model.AnimalHealthStatus;
import com.example.animalshelter.model.AnimalType;
import com.example.animalshelter.service.AnimalNotFoundException;
import com.example.animalshelter.service.AnimalService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AnimalControllerTest {

    @Mock
    private AnimalService animalService;

    @InjectMocks
    private AnimalController animalController;


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
        Mockito.when(animalService.add(dummyAnimal)).thenReturn(true);

        String result = animalController.addAnimal(dummyAnimal);

        assertThat(result).isEqualTo("The animal has been added successfully.");
    }

    @Test
    public void shouldNotAddAnAnimal() {
        Mockito.when(animalService.add(dummyAnimal)).thenReturn(false);

        String result = animalController.addAnimal(dummyAnimal);

        assertThat(result).isEqualTo("An error occurred during adding animal.");
    }

    @Test
    public void shouldGetAllAnimals() {
        Mockito.when(animalService.findAll()).thenReturn(Collections.singletonList(dummyAnimal));

        List<Animal> animals = animalController.all();

        assertThat(animals).isEqualTo(Collections.singletonList(dummyAnimal));
    }

    @Test
    public void shouldGetOneAnimal() {
        Mockito.when(animalService.findOne(4321L)).thenReturn(dummyAnimal);

        Animal animal = animalController.one(4321L);

        assertThat(animal).isEqualTo(dummyAnimal);
    }

    @Test
    public void shouldThrowAnimalNotFoundExceptionWhenAnimalIsNotFound() {
        Mockito.when(animalService.findOne(1L)).thenThrow(new AnimalNotFoundException(1L));

        assertThatThrownBy(() -> animalController.one(1L))
        .isInstanceOf(AnimalNotFoundException.class)
        .hasMessage("Could not find the animal with id: 1.");
    }
}