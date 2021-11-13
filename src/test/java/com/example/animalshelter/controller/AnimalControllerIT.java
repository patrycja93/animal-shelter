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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AnimalControllerIT {

    public static final String INVALID_ANIMAL_ID_NUMBER = "Invalid animal id number.";
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
        String responseAsJson = new ObjectMapper().writeValueAsString(new AnimalDto(ID));

        this.mockMvc.perform(
                        post("/animals")
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
                        post("/animals")
                                .content(asJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnPositiveHttpResponseWhenDeletedAnimalSuccessfully() throws Exception {
        String asJson = new ObjectMapper().writeValueAsString(DUMMY_ANIMAL);

        this.mockMvc.perform(
                post("/animals")
                        .content(asJson)
                        .contentType(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(
                        delete("/animals/{id}", DUMMY_ANIMAL.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldThrowExceptionWhenIdIsNegative() throws Exception {
        this.mockMvc.perform(
                        delete("/animals/{id}", -1)
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
                        delete("/animals/{id}", 1)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof AnimalNotFoundException))
                .andExpect(result -> assertEquals(
                        "Animal with provided id doesn't exist in the database.",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}
