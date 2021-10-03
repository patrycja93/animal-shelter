package com.example.animalshelter.model;

import com.example.animalshelter.service.AnimalService;
import lombok.Data;
import org.hibernate.annotations.Table;

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
