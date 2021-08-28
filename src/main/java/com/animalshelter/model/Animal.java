package com.animalshelter.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Keeps basic information about an animal
 */
@Getter
@Setter
public class Animal {

    private int id;
    private String name;
    private String type;
    private String gender;
    private int age;
    private String status;
}
