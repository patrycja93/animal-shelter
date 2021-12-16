package com.example.animalshelter;

import com.example.animalshelter.controller.AnimalDto;
import com.example.animalshelter.model.Animal;
import com.example.animalshelter.model.AnimalGender;
import com.example.animalshelter.model.AnimalHealthStatus;
import com.example.animalshelter.model.AnimalType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class AnimalTestUtils {

    public static final Long NEGATIVE_ID_NUMBER = -1L;
    public static final Long ID = 4321L;

    public static final Animal DUMMY_ANIMAL = Animal.builder()
            .id(ID)
            .name("Axel")
            .age(3)
            .type(AnimalType.DOG)
            .gender(AnimalGender.MALE)
            .healthStatus(AnimalHealthStatus.HEALTHY)
            .build();

    public final static String INVALID_ID_MSG = "Invalid animal id number.";

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static String writeValueAsString(Object object) {
        return objectMapper.writeValueAsString(object);
    }
}
