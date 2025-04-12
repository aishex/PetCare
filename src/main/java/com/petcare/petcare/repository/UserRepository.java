package com.petcare.petcare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcare.petcare.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

    List<User> findByName(String name);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}