package com.dodson.petclinic.repositories;

import com.dodson.petclinic.model.Owner;

import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    
}
