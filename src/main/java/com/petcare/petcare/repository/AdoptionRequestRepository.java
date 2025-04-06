package com.petcare.petcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcare.petcare.model.AdoptionRequest;

public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Long>{

    List<AdoptionRequest> findByStatus(String status);
}