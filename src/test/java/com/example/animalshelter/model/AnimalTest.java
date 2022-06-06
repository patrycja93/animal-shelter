package com.example.animalshelter.model;

import com.example.animalshelter.AnimalTestUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnimalTest {

    @Test
    public void shouldTwoAnimalsBeEqual() {
        Animal animalOne = AnimalTestUtils.DUMMY_ANIMAL;
        Animal animalTwo = AnimalTestUtils.DUMMY_ANIMAL;

        assertThat(animalOne).isEqualTo(animalTwo);
    }

    @Test
    public void shouldTwoAnimalsBeEqualWithSameId() {
        Animal animalOne = new Animal("Axel",3,AnimalType.DOG,AnimalGender.MALE,AnimalHealthStatus.HEALTHY);
        Animal animalTwo = new Animal("Axel",3,AnimalType.DOG,AnimalGender.MALE,AnimalHealthStatus.HEALTHY);

        assertThat(animalOne.equals(animalTwo)).isTrue();
    }
}