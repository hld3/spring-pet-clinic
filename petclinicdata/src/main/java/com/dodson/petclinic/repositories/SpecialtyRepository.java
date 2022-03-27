package com.dodson.petclinic.repositories;

import com.dodson.petclinic.model.Specialty;

import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
    
}
