package com.example.animalshelter.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Keeps basic information about an animal
 */
@Getter
@Setter
@ToString
public class Animal {

    private int id;
    private String name;
    private String type;
    private String gender;
    private int age;

    // In the future we can switch this to enum to represent animal health status.
    private String status;
}
