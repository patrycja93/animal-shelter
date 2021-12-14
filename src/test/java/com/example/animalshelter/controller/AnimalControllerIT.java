package com.example.animalshelter.controller;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.model.AnimalGender;
import com.example.animalshelter.model.AnimalHealthStatus;
import com.example.animalshelter.model.AnimalType;
import com.example.animalshelter.service.AnimalNotFoundException;
import com.example.animalshelter.service.AnimalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AnimalControllerIT {

    @Autowired
    private AnimalController animalController;

    @Autowired
    private MockMvc mockMvc;

    private final Animal dummyAnimal = createAnimal();
    private final Animal secondDummyAnimal = createSecondAnimal();

    private Animal createAnimal() {
        Animal dummyAnimal = new Animal();
        dummyAnimal.setId(4321);
        dummyAnimal.setAge(3);
        dummyAnimal.setGender(AnimalGender.MALE);
        dummyAnimal.setHealthStatus(AnimalHealthStatus.HEALTHY);
        dummyAnimal.setName("Alex");
        dummyAnimal.setType(AnimalType.DOG);
        return dummyAnimal;
    }

    private Animal createSecondAnimal() {
        Animal dummyAnimal = new Animal();
        dummyAnimal.setId(4321);
        dummyAnimal.setAge(3);
        dummyAnimal.setGender(AnimalGender.MALE);
        dummyAnimal.setHealthStatus(AnimalHealthStatus.HEALTHY);
        dummyAnimal.setName("Alex");
        dummyAnimal.setType(AnimalType.DOG);
        return dummyAnimal;
    }

    @Test
    public void contextLoad() {
        assertThat(animalController).isNotNull();
    }

    @Test
    public void shouldReturnPositiveHttpResponseWithContent() throws Exception {
        String asJson = new ObjectMapper().writeValueAsString(dummyAnimal);

        this.mockMvc.perform(
                post("/animals")
                        .content(asJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("The animal has been added successfully."));
    }

    @Test
    public void shouldReturnPositiveHttpResponseWithEmptyAnimalObjectWhenAnimalIsEmpty() throws Exception {
        String asJson = new ObjectMapper().writeValueAsString(null);

        this.mockMvc.perform(
                post("/animals")
                        .content(asJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnStatusIsOkAndAnimalObjectList() throws Exception {
        List<Animal> animals = List.of(dummyAnimal, secondDummyAnimal);
        animalController.addAnimal(dummyAnimal);
        animalController.addAnimal(secondDummyAnimal);
        String animal = new ObjectMapper().writeValueAsString(animals);

        this.mockMvc.perform(
                get("/animals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().string(animal))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusIsOkAndAnimal() throws Exception {
        animalController.addAnimal(dummyAnimal);
        String animal = new ObjectMapper().writeValueAsString(dummyAnimal);

        this.mockMvc.perform(
                get("/animals/4321")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().string(animal));
    }

    @Test
    public void shouldReturnStatusNotFoundWhenAnimalIsNotFound() throws Exception {
        animalController.addAnimal(dummyAnimal);

        this.mockMvc.perform(
                get("/animals/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof AnimalNotFoundException))
                .andExpect(result -> assertEquals("Could not find the animal with id: 1.", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}
