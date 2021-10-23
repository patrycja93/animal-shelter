package com.example.animalshelter.service;

import com.example.animalshelter.model.Animal;

import java.util.List;

public interface AnimalService {

    boolean add(Animal animal);

    List<Animal> findAll();
}
