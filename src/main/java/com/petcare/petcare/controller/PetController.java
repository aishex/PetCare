package com.petcare.petcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.petcare.petcare.model.Pet;
import com.petcare.petcare.repository.PetRepository;
import java.util.List;

@RestController
public class PetController{

    private final PetRepository petRepository;

    @Autowired
    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping("/pets")
    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }

    @PostMapping("/pets")
    public Pet createPet(@RequestBody Pet pet) {
        return petRepository.save(pet);
    }

    @PutMapping("/pets/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet petDetails) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        pet.setName(petDetails.getName());
        pet.setSpecies(petDetails.getSpecies());
        pet.setAge(petDetails.getAge());

        return petRepository.save(pet);
    }

    @DeleteMapping("/pets/{id}")
    public void deletePet(@PathVariable Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        petRepository.delete(pet);
    }

}