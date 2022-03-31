package com.dodson.petclinic.services.map;

import java.util.Set;

import com.dodson.petclinic.model.Visit;
import com.dodson.petclinic.services.VisitService;

import org.springframework.stereotype.Service;

@Service
public class VisitServiceMap extends AbstractServiceMap<Visit, Long> implements VisitService {

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit object) {

        if(object.getPet() == null || object.getPet().getOwner() == null || object.getPet().getId() == null || 
            object.getPet().getOwner().getId() == null) {
                throw new RuntimeException("Invalid Visit");
            }
        return super.save(object);
    }
    
}
