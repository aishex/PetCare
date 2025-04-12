package com.petcare.petcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.petcare.petcare.model.AdoptionRequest;
import com.petcare.petcare.service.AdoptionRequestService;

@RestController
@RequestMapping("/api/adoption_requests")
public class AdoptionRequestController{

    @Autowired
    private AdoptionRequestService adoptionRequestService;

    //all 
    @GetMapping
    public List<AdoptionRequest> getAllAdoptionRequests(){
        return adoptionRequestService.getAllAdoptionRequests();
    }

    //by id
    @GetMapping("/{id}")
    public ResponseEntity<AdoptionRequest> getAdoptionRequestById(@PathVariable Long id){
        AdoptionRequest adoptionRequest = adoptionRequestService.getAdoptionRequestById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found"));
        return ResponseEntity.ok(adoptionRequest);
    }

    //add, edit
    @PostMapping
    public ResponseEntity<AdoptionRequest> saveAdoptionRequest(@RequestBody AdoptionRequest adoptionRequest){
        AdoptionRequest savedRequest = adoptionRequestService.saveAdoptionRequest(adoptionRequest);
        return ResponseEntity.ok(savedRequest);
    }

    //delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdoptionRequestById(@PathVariable Long id){
        boolean removed = adoptionRequestService.deleteAdoptionRequest(id);
        if (removed) {
            return ResponseEntity.ok("Deleted successfully");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found");
        }
    }
}
