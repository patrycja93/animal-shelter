package com.example.animalshelter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * Keeps basic information about an animal
 */
@Entity
@NoArgsConstructor
//@AllArgsConstructor
@Getter
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private AnimalType type;

    @Enumerated(EnumType.STRING)
    private AnimalGender gender;

    @Enumerated(EnumType.STRING)
    private AnimalHealthStatus healthStatus;

    public Animal(String name, int age, AnimalType type, AnimalGender gender, AnimalHealthStatus healthStatus) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.gender = gender;
        this.healthStatus = healthStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age && name.equals(animal.name) && type == animal.type && gender == animal.gender && healthStatus == animal.healthStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, type, gender, healthStatus);
    }
}
