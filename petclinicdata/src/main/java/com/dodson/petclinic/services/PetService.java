package com.dodson.petclinic.services;

import java.util.Set;
import com.dodson.petclinic.model.Pet;

public interface PetService {
    
    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}
