package com.dodson.petclinic.model;

public class PetType extends BaseEntity {

    private final String name;

    public PetType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
