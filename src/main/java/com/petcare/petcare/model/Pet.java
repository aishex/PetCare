package com.petcare.petcare.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Entity 
@Table(name = "pets")
public class Pet{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int age;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String size; 
    private String description;
    private String imageUrl;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="owner_id")
    private User owner;

    @OneToMany(mappedBy = "pet")
    private List<AdoptionRequest> adoptionRequests;

    public void setOwner(User owner){
        this.owner = owner;
    }
}
