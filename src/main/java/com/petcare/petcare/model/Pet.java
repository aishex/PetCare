package com.petcare.petcare.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity 
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int age;
    private String name;
    private String species; // cat, dog
    private String breed; // ragdoll, doberman
    private String gender;
    private String size; // small, medium, large
    private String description;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "pet")
    private List<AdoptionRequest> adoptionRequests;
}