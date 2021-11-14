package com.example.animalshelter.controller;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.model.AnimalGender;
import com.example.animalshelter.model.AnimalHealthStatus;
import com.example.animalshelter.model.AnimalType;
import com.example.animalshelter.service.AddAnimalException;
import com.example.animalshelter.service.AnimalService;
import com.example.animalshelter.service.AnimalNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AnimalControllerTest {

    public static final int ID = 4321;
    public static final int NEGATIVE_ID_NUMBER = -1;

    public static final String INVALID_ANIMAL_ID_NUMBER = "Invalid animal id number.";
    public static final String ERROR_DURING_SAVING_AN_ANIMAL_IN_DATABASE = "Error during saving an animal in database";
    public static final String COULD_NOT_CREATE_THE_ANIMAL = "Could not create the animal Error during saving an animal in database.";

    public final AnimalService animalService = mock(AnimalService.class);

    public static final Animal DUMMY_ANIMAL = Animal.builder()
            .id(ID)
            .name("Axel")
            .age(3)
            .type(AnimalType.DOG)
            .gender(AnimalGender.MALE)
            .healthStatus(AnimalHealthStatus.HEALTHY)
            .build();

    public static final AnimalDto DUMMY_ANIMAL_DTO = new AnimalDto(ID);

    @Test
    public void shouldAddAnAnimal() {
        AnimalController animalController = new AnimalController(animalService);
        when(animalService.add(DUMMY_ANIMAL)).thenReturn(DUMMY_ANIMAL_DTO);

        AnimalDto result = animalController.addAnimal(DUMMY_ANIMAL);

        assertThat(result.getId()).isEqualTo(ID);
    }

    @Test
    public void shouldNotAddAnAnimal() {
        AnimalController animalController = new AnimalController(animalService);
        when(animalService.add(DUMMY_ANIMAL))
                .thenThrow(new AddAnimalException(ERROR_DURING_SAVING_AN_ANIMAL_IN_DATABASE));

        assertThatExceptionOfType(AddAnimalException.class)
                .isThrownBy(() -> animalController.addAnimal(DUMMY_ANIMAL))
                .withMessage(COULD_NOT_CREATE_THE_ANIMAL);
    }

    @Test
    public void shouldDeleteAnAnimal() throws AnimalNotFoundException {
        AnimalController animalController = new AnimalController(animalService);
        animalController.deleteAnimal(ID);

        verify(animalService, times(1)).delete(ID);
    }

    @Test
    public void shouldNotDeleteAnAnimal() throws AnimalNotFoundException {
        AnimalController animalController = new AnimalController(animalService);
        when(animalService.delete(NEGATIVE_ID_NUMBER))
                .thenThrow(new AnimalNotFoundException(INVALID_ANIMAL_ID_NUMBER));

        assertThatExceptionOfType(AnimalNotFoundException.class)
                .isThrownBy(() -> animalController.deleteAnimal(NEGATIVE_ID_NUMBER))
                .withMessage(INVALID_ANIMAL_ID_NUMBER);
    }

    @Test
    public void shouldUpdateAnimal() throws AnimalNotFoundException {
        AnimalController animalController = new AnimalController(animalService);
        animalController.updateAnimal(DUMMY_ANIMAL);

        verify(animalService, times(1))
                .update(DUMMY_ANIMAL);
    }

    @Test
    public void shouldNotUpdateAnimal() throws AnimalNotFoundException {
        Animal animalWithNegativeId = Animal.builder().id(NEGATIVE_ID_NUMBER).build();
        AnimalController animalController = new AnimalController(animalService);

        doThrow(new AnimalNotFoundException(INVALID_ANIMAL_ID_NUMBER))
                .when(animalService).update(animalWithNegativeId);

        assertThatExceptionOfType(AnimalNotFoundException.class)
                .isThrownBy(() -> animalController.updateAnimal(animalWithNegativeId))
                .withMessage(INVALID_ANIMAL_ID_NUMBER);
    }
}
