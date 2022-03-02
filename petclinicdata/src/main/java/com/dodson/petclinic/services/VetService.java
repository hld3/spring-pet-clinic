package com.dodson.petclinic.services;

import java.util.Set;
import com.dodson.petclinic.model.Vet;

public interface VetService {
    
    Vet findById(Long id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}
