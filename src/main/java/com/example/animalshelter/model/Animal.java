package com.example.animalshelter.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Keeps basic information about an animal
 */
@Data
@Table
@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    @Enumerated(EnumType.STRING)
    private AnimalType type;

    @Column
    @Enumerated(EnumType.STRING)
    private AnimalGender gender;

    @Column
    @Enumerated(EnumType.STRING)
    private AnimalHealthStatus healthStatus;

    public Animal() {
    }

    public Animal(String name, int age, AnimalType type, AnimalGender gender, AnimalHealthStatus healthStatus) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.gender = gender;
        this.healthStatus = healthStatus;
    }

    public Animal(Long id, String name, int age, AnimalType type, AnimalGender gender, AnimalHealthStatus healthStatus) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.type = type;
        this.gender = gender;
        this.healthStatus = healthStatus;
    }
}
