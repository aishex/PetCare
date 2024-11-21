package com.petcare.petcare.repository;

import com.petcare.petcare.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByName(String name);
    List<Pet> findBySpecies(String species);
    List<Pet> findByAge(int age);
}

