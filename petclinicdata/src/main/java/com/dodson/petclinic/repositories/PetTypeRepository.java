package com.dodson.petclinic.repositories;

import com.dodson.petclinic.model.PetType;

import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
    
}
