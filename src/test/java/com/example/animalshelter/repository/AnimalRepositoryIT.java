package com.example.animalshelter.repository;

import com.example.animalshelter.model.Animal;
import com.example.animalshelter.model.AnimalGender;
import com.example.animalshelter.model.AnimalHealthStatus;
import com.example.animalshelter.model.AnimalType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.activation.DataSource;
import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AnimalRepositoryIT {


    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void testContext() {
        assertThat(animalRepository).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
    }

    @Test
    public void shouldAnimalBeSaved() {
        animalRepository.save(new Animal());
        assertThat(animalRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void shouldFourAnimalBeSaved() {
        animalRepository.save(new Animal());
        animalRepository.save(new Animal());
        animalRepository.save(new Animal());
        animalRepository.save(new Animal());
        assertThat(animalRepository.findAll().size()).isEqualTo(4);
    }

    @Test
    public void shouldNotSaveSameAnimalsTwice() {
        Animal animal = new Animal();

        animalRepository.save(animal);
        animalRepository.save(animal);

        assertThat(animalRepository.findById(1L).get()).isEqualTo(animal);
        assertThat(animalRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void shouldNotSaveSameAnimalsWithSameDataTwice() {
        Animal animal = new Animal("Pixi",5, AnimalType.BIRD, AnimalGender.FEMALE, AnimalHealthStatus.HEALTHY);

        animalRepository.save(animal);
        animalRepository.save(animal);

        assertThat(animalRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void shouldSaveTwoAnimalsWithSameData() {
        Animal animal1 = new Animal("Pixi",5, AnimalType.BIRD, AnimalGender.FEMALE, AnimalHealthStatus.HEALTHY);
        Animal animal2 = new Animal("Pixi",5, AnimalType.BIRD, AnimalGender.FEMALE, AnimalHealthStatus.HEALTHY);

        animalRepository.save(animal1);
        animalRepository.save(animal2);

        assertThat(animal1).isEqualTo(animal2);
        assertThat(animalRepository.findAll().size()).isEqualTo(2);
    }
}