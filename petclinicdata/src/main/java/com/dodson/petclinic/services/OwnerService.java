package com.dodson.petclinic.services;

import com.dodson.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    
    Owner findByLastName(String lastName);
}
