package com.example.animalshelter.controller;

import com.example.animalshelter.model.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class AnimalCreatedResponse {

    private final Animal animal;
}
