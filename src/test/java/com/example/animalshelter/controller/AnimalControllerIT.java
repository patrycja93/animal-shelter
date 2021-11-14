package com.example.animalshelter.controller;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.model.AnimalGender;
import com.example.animalshelter.model.AnimalHealthStatus;
import com.example.animalshelter.model.AnimalType;
import com.example.animalshelter.service.AnimalNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AnimalControllerIT {

    public static final String INVALID_ANIMAL_ID_NUMBER = "Invalid animal id number.";
    public static final String ANIMAL_DOES_NOT_EXIST = "Animal with provided id doesn't exist in the database.";
    public static final int NEGATIVE_ID_NUMBER = -1;
    public static final int ID = 4321;
    public static final Animal DUMMY_ANIMAL = Animal.builder()
            .id(4321)
            .name("Axel")
            .age(3)
            .type(AnimalType.DOG)
            .gender(AnimalGender.MALE)
            .healthStatus(AnimalHealthStatus.HEALTHY)
            .build();

    public static final AnimalDto DUMMY_ANIMAL_DTO = new AnimalDto(ID);
    public static final String URL_ANIMALS = "/animals";
    public static final String URL_ANIMALS_ID = "/animals/{id}";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AnimalController animalController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoad() {
        assertThat(animalController).isNotNull();
    }

    @Test
    public void shouldReturnPositiveHttpResponseWhenAddedAnimalSuccessfully() throws Exception {
        String asJson = new ObjectMapper().writeValueAsString(DUMMY_ANIMAL);
        String responseAsJson = new ObjectMapper().writeValueAsString(DUMMY_ANIMAL_DTO);

        this.mockMvc.perform(
                        post(URL_ANIMALS)
                                .content(asJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(responseAsJson));
    }

    @Test
    public void shouldReturnBadRequestStatusWhenAnimalIsNull() throws Exception {
        String asJson = new ObjectMapper().writeValueAsString(null);

        this.mockMvc.perform(
                        post(URL_ANIMALS)
                                .content(asJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnPositiveHttpResponseWhenDeletedAnimalSuccessfully() throws Exception {
        String asJson = new ObjectMapper().writeValueAsString(DUMMY_ANIMAL);

        this.mockMvc.perform(
                post(URL_ANIMALS)
                        .content(asJson)
                        .contentType(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(
                        delete(URL_ANIMALS_ID, DUMMY_ANIMAL.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldThrowExceptionWhenIdIsNegative() throws Exception {
        this.mockMvc.perform(
                        delete(URL_ANIMALS_ID, NEGATIVE_ID_NUMBER)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof AnimalNotFoundException))
                .andExpect(result -> assertEquals(
                        INVALID_ANIMAL_ID_NUMBER,
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void shouldThrowExceptionWhenIdNotFound() throws Exception {
        this.mockMvc.perform(
                        delete(URL_ANIMALS_ID, 1)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof AnimalNotFoundException))
                .andExpect(result -> assertEquals(
                        ANIMAL_DOES_NOT_EXIST,
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    // TODO(fornalik): This test did not check the full update flow
    // We should modify this test (or create new one) to check if the Animal was really changed,
    // when the GET request will be ready
    @Test
    public void shouldReturnPositiveHttpResponseWhenUpdatedAnimalSuccessfully() throws Exception {
        String asJson = new ObjectMapper().writeValueAsString(DUMMY_ANIMAL);

        this.mockMvc.perform(
                post(URL_ANIMALS)
                        .content(asJson)
                        .contentType(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(
                        put(URL_ANIMALS)
                                .content(asJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldThrowExceptionOnUpdateWhenIdNotFound() throws Exception {
        String asJson = new ObjectMapper().writeValueAsString(DUMMY_ANIMAL);

        this.mockMvc.perform(
                        put(URL_ANIMALS)
                                .content(asJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof AnimalNotFoundException))
                .andExpect(result -> assertEquals(
                        ANIMAL_DOES_NOT_EXIST,
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void shouldThrowExceptionOnUpdateWhenIdIsNegative() throws Exception {
        String asJson = new ObjectMapper()
                .writeValueAsString(Animal.builder().id(NEGATIVE_ID_NUMBER).build());

        this.mockMvc.perform(
                        put(URL_ANIMALS)
                                .content(asJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof AnimalNotFoundException))
                .andExpect(result -> assertEquals(INVALID_ANIMAL_ID_NUMBER,
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

}
