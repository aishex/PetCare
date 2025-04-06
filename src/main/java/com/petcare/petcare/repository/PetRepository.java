package com.petcare.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.petcare.petcare.model.Pet;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long>{

    List<Pet> findByName(String name);
}