package com.dodson.petclinic.model;

import java.time.LocalDate;

public class Pet extends BaseEntity {
    
    private String name;
    private LocalDate birthDate;
    private PetType PetType;
    private Owner owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public PetType getPetType() {
        return PetType;
    }

    public void setPetType(PetType petType) {
        PetType = petType;
    }
}
