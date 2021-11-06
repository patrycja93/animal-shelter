package com.example.animalshelter.controller;

import com.example.animalshelter.model.Animal;
import lombok.Data;

@Data
public class AnimalDeletedResponse {

    private final Animal deletedAnimal;
}
