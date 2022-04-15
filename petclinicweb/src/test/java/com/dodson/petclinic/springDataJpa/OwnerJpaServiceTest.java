package com.dodson.petclinic.springDataJpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.dodson.petclinic.model.Owner;
import com.dodson.petclinic.repositories.OwnerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class  )
class OwnerJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerJpaService service;

    String expectedLastName = "Smith";
    Owner expectedOwner;

    @BeforeEach
    void setUp() {
        expectedOwner = Owner.builder().id(1L).lastName(expectedLastName).build();
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        var ownerOne = Owner.builder().id(1L).build();
        var ownerTwo = Owner.builder().id(2L).build();
        ownerSet.add(ownerOne);
        ownerSet.add(ownerTwo);

        when(ownerRepository.findAll()).thenReturn(ownerSet);

        var result = service.findAll();

        assertEquals(ownerSet, result);
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(expectedOwner));

        var result = service.findById(1L);

        assertEquals(expectedOwner, result);
    }

    @Test
    void findByIdNull() {
        var result = service.findById(1L);

        assertNull(result);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(expectedOwner);

        var result = service.save(expectedOwner);
        
        verify(ownerRepository).save(expectedOwner);
        assertEquals(expectedOwner, result);
    }

    @Test
    void delete() {
        service.delete(expectedOwner);

        verify(ownerRepository).delete(expectedOwner);
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(ownerRepository).deleteById(1L);
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(expectedOwner);

        var result = service.findByLastName(expectedLastName);

        verify(ownerRepository).findByLastName(expectedLastName);
        assertEquals(expectedOwner, result);
    }
}
