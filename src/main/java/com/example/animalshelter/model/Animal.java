package com.example.animalshelter.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Keeps basic information about an animal
 */
@Data
@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
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
}
