package com.example.animalshelter.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Keeps basic information about an animal
 */
@Data
@Builder
public class Animal {

    @NotNull
    @Positive
    private Long id;
    
    private String name;
    private int age;
    private AnimalType type;
    private AnimalGender gender;
    private AnimalHealthStatus healthStatus;
}
