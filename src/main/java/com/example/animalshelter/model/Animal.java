package com.example.animalshelter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Keeps basic information about an animal
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

}
