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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AnimalControllerTest {

    private final static String INVALID_ID_MSG = "Invalid animal id number.";
    public static final int NEGATIVE_ID_NUMBER = -1;

    private final AnimalService animalService = Mockito.mock(AnimalService.class);
    private static final int ID = 4321;

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
        Mockito.when(animalService.add(DUMMY_ANIMAL)).thenReturn(DUMMY_ANIMAL_DTO);

        AnimalDto result = animalController.addAnimal(DUMMY_ANIMAL);

        assertThat(result.getId()).isEqualTo(ID);
    }

    @Test
    public void shouldNotAddAnAnimal() {
        AnimalController animalController = new AnimalController(animalService);
        Mockito.when(animalService.add(DUMMY_ANIMAL))
                .thenThrow(new AddAnimalException("Error during saving an animal in database"));

        assertThatExceptionOfType(AddAnimalException.class)
                .isThrownBy(() -> animalController.addAnimal(DUMMY_ANIMAL))
                .withMessage("Could not create the animal Error during saving an animal in database.");
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
        Mockito.when(animalService.delete(NEGATIVE_ID_NUMBER))
                .thenThrow(new AnimalNotFoundException(INVALID_ID_MSG));

        assertThatExceptionOfType(AnimalNotFoundException.class)
                .isThrownBy(() -> animalController.deleteAnimal(NEGATIVE_ID_NUMBER))
                .withMessage("Invalid animal id number.");
    }
}
