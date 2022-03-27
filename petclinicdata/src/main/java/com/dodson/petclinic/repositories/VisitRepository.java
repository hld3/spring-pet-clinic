package com.dodson.petclinic.repositories;

import com.dodson.petclinic.model.Visit;

import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
    
}
