package com.example.animalshelter.controller;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.model.AnimalGender;
import com.example.animalshelter.model.AnimalHealthStatus;
import com.example.animalshelter.model.AnimalType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.assertj.core.api.Assertions.*;
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

    @Test
    public void contextLoad() {
        assertThat(animalController).isNotNull();
    }

    @Test
    public void shouldReturnPositiveHttpResponseWithContent() throws Exception {
        Animal dummyAnimal = new Animal();
        dummyAnimal.setId(4321);
        dummyAnimal.setAge(3);
        dummyAnimal.setGender(AnimalGender.MALE);
        dummyAnimal.setHealthStatus(AnimalHealthStatus.HEALTHY);
        dummyAnimal.setName("Alex");
        dummyAnimal.setType(AnimalType.DOG);

        String asJson = new ObjectMapper().writeValueAsString(dummyAnimal);

        this.mockMvc.perform(
                        post("/animals")
                                .content(asJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Animal added successfully."));
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
}
