package com.dodson.petclinic.repositories;

import com.dodson.petclinic.model.Pet;

import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
    
}