package com.example.animalshelter.controller;

import com.example.animalshelter.AnimalTestUtils;
import com.example.animalshelter.model.Animal;
import com.example.animalshelter.service.AddAnimalException;
import com.example.animalshelter.service.AnimalService;
import com.example.animalshelter.service.AnimalNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

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
                .withMessage(INVALID_ID_MSG);
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

        doThrow(new AnimalNotFoundException(INVALID_ID_MSG))
                .when(animalService).update(animalWithNegativeId);

        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> animalController.updateAnimal(animalWithNegativeId))
                .withMessage(INVALID_ID_MSG_CONTROLLER_EXCEPTION);
    }
}
