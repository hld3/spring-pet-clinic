package com.dodson.petclinic.repositories;

import java.util.List;

import com.dodson.petclinic.model.Owner;

import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
