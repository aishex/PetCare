package com.petcare.petcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.petcare.petcare.repository.AdoptionRequestRepository;
import com.petcare.petcare.model.AdoptionRequest;

@Service
public class AdoptionRequestService{

    @Autowired
    public AdoptionRequestRepository adoptionRequestRepository;

    public AdoptionRequest saveAdoptionRequest(AdoptionRequest adoptionRequest){
        return adoptionRequestRepository.save(adoptionRequest);
    }

    public List<AdoptionRequest> getAllAdoptionRequests(){
        return adoptionRequestRepository.findAll();
    }

    public Optional<AdoptionRequest> getAdoptionRequestById(Long id){
        return adoptionRequestRepository.findById(id);
    }

}