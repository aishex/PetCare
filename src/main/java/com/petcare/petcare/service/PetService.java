package com.petcare.petcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.petcare.petcare.repository.PetRepository;
import com.petcare.petcare.model.Pet;

@Service
public class PetService{

    @Autowired
    public PetRepository petRepository;

    public Pet savePet(Pet pet){
        return petRepository.save(pet);
    }

    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById(Long id){
        return petRepository.findById(id);
    }

    public boolean deletePet(Long id){
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()){
            petRepository.delete(pet.get());
            return true;
        }
        return false;
    }
}