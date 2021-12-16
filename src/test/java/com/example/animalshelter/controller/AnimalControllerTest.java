package com.example.animalshelter.controller;

import com.example.animalshelter.AnimalTestUtils;
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

class AnimalControllerTest extends AnimalTestUtils {

    private final AnimalService animalService = Mockito.mock(AnimalService.class);


    @Test
    public void shouldAddAnAnimal() {
        AnimalController animalController = new AnimalController(animalService);
        Mockito.when(animalService.add(DUMMY_ANIMAL)).thenReturn(DUMMY_ANIMAL);

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
