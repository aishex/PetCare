package com.petcare.petcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.petcare.petcare.model.Pet;
import com.petcare.petcare.model.User;
import com.petcare.petcare.repository.UserRepository;
import com.petcare.petcare.service.PetService;

@RestController
@RequestMapping("/api/pets")
public class PetController{

    @Autowired
    private PetService petService;

    @Autowired
    private UserRepository userRepository;

    //all 
    @GetMapping
    public List<Pet> getAllPets(){
        return petService.getAllPets();
    }

    //by id
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id){
        Pet pet = petService.getPetById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found"));
        return ResponseEntity.ok(pet);
    }

    //add, edit
    @PostMapping
    public ResponseEntity<Pet> savePet(@RequestBody Pet pet, @AuthenticationPrincipal UserDetails userDetails) {
        User owner = userRepository.findByEmail(userDetails.getUsername())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        pet.setOwner(owner);

        Pet savedPet = petService.savePet(pet);
        return ResponseEntity.ok(savedPet);
    }

    //delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePetById(@PathVariable Long id){
        boolean removed = petService.deletePet(id);
        if (removed){
            return ResponseEntity.ok("Deleted successfully");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found");
        }
    }
}

