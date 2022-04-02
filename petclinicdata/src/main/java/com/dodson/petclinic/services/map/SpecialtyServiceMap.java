package com.dodson.petclinic.services.map;

import java.util.Set;

import com.dodson.petclinic.model.Specialty;
import com.dodson.petclinic.services.SpecialtyService;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class SpecialtyServiceMap extends AbstractServiceMap<Specialty, Long> implements SpecialtyService {

    @Override
    public void delete(Specialty object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Set<Specialty> findAll() {
        return super.findAll();
    }

    @Override
    public Specialty findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Specialty save(Specialty object) {
        return super.save(object);
    }
    
}
