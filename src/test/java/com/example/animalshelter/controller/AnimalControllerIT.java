package com.example.animalshelter.controller;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.model.AnimalGender;
import com.example.animalshelter.model.AnimalHealthStatus;
import com.example.animalshelter.model.AnimalType;
import com.example.animalshelter.service.AddAnimalException;
import com.example.animalshelter.service.DeleteAnimalException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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

    public static final Animal DUMMY_ANIMAL = Animal.builder()
            .id(4321)
            .name("Axel")
            .age(3)
            .type(AnimalType.DOG)
            .gender(AnimalGender.MALE)
            .healthStatus(AnimalHealthStatus.HEALTHY)
            .build();

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

        this.mockMvc.perform(
                        post("/animals")
                                .content(asJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Request completed successfully"));
    }

    //Actually this test is broken, repository is true when we call add() method.
    //TODO: Probably we should use mock here.
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
                        delete("/animals/{id}", DUMMY_ANIMAL.getId())
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Request completed successfully"));
    }


    @Test
    public void shouldThrowExceptionWhenIdIsNegative() throws Exception {
        this.mockMvc.perform(
                        delete("/animals/{id}", -1)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof DeleteAnimalException))
                .andExpect(result -> assertEquals(
                        "Invalid animal id number",
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
                        result.getResolvedException() instanceof DeleteAnimalException))
                .andExpect(result -> assertEquals(
                        "Animal with provided id doesn't exist in the database.",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}
