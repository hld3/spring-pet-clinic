package com.dodson.petclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

import com.dodson.petclinic.model.Owner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;
    final Long ownerId = 1L;
    final String lastName = "Britches";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner = Owner.builder().id(id).build();
        Owner saved = ownerServiceMap.save(owner);

        assertEquals(id, saved.getId());
    }

    @Test
    void saveNoId() {
        Owner saved = ownerServiceMap.save(new Owner());

        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    void delete() {
        assertEquals(1, ownerServiceMap.findAll().size());
        
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        assertEquals(1, ownerServiceMap.findAll().size());
        
        ownerServiceMap.deleteById(ownerId);;

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner found = ownerServiceMap.findByLastName(lastName);

        assertNotNull(found);
        assertEquals(lastName, found.getLastName());
    }

    @Test
    void findByLastNameNull() {
        Owner found = ownerServiceMap.findByLastName("lastName");

        assertNull(found);
    }
}