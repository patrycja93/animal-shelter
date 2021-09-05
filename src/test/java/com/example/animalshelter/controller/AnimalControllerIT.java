package com.example.animalshelter.controller;

import com.example.animalshelter.model.Animal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
        Assertions.assertThat(animalController).isNotNull();
    }

    @Test
    public void shouldReturnPositiveHttpResponseWithContent() throws Exception {
        Animal dummyAnimal = new Animal();
        dummyAnimal.setId(4321);
        dummyAnimal.setAge(3);
        dummyAnimal.setGender("male");
        dummyAnimal.setStatus("healthy");
        dummyAnimal.setName("Alex");
        dummyAnimal.setType("dog");

        String asJson = new ObjectMapper().writeValueAsString(dummyAnimal);

        this.mockMvc.perform(
                        post("/save")
                                .content(asJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":4321,\"name\":\"Alex\",\"type\":\"dog\",\"gender\":\"male\",\"age\":3,\"status\":\"healthy\"}"));
    }

    @Test
    public void shouldReturnPositiveHttpResponseWithEmptyContentIfIdIsMissing() throws Exception {
        Animal dummyAnimal = new Animal();
        dummyAnimal.setAge(3);
        dummyAnimal.setGender("male");
        dummyAnimal.setStatus("healthy");
        dummyAnimal.setName("Alex");
        dummyAnimal.setType("dog");

        String asJson = new ObjectMapper().writeValueAsString(dummyAnimal);

        this.mockMvc.perform(
                        post("/save")
                                .content(asJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":0,\"name\":null,\"type\":null,\"gender\":null,\"age\":0,\"status\":null}"));
    }

    @Test
    public void shouldReturnPositiveHttpResponseWithEmptyAnimalObjectWhenAnimalIsEmpty() throws Exception {
        Animal dummyAnimal = new Animal();
        String asJson = new ObjectMapper().writeValueAsString(dummyAnimal);

        this.mockMvc.perform(
                        post("/save")
                                .content(asJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":0,\"name\":null,\"type\":null,\"gender\":null,\"age\":0,\"status\":null}"));
    }
}
