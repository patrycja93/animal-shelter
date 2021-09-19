package com.example.animalshelter.model;

import lombok.Data;

/**
 * Keeps basic information about an animal
 */
@Data
public class Animal {

    private int id;
    private String name;
    private int age;
    private AnimalType type;
    private AnimalGender gender;
    private AnimalHealthStatus healthStatus;
}
