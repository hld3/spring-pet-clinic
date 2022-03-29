package com.dodson.petclinic.springDataJpa;

import java.util.HashSet;
import java.util.Set;

import com.dodson.petclinic.model.Pet;
import com.dodson.petclinic.repositories.PetRepository;
import com.dodson.petclinic.services.PetService;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springDataJpa")
public class PetJpaService implements PetService {

    private final PetRepository petRepository;

    public PetJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
    
}
