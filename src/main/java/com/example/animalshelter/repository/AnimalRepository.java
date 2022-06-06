package com.example.animalshelter.repository;

import com.example.animalshelter.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Database operation for Animal entity.
 */
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
