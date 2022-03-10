package com.dodson.petclinic.services.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.dodson.petclinic.model.BaseEntity;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Number> {
    
    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll() { return new HashSet<>(map.values()); }

    T findById(ID id) { return map.get(id); }

    T save(T object) { 

        if(object != null) {
            if(object.getId() == null) {
                object.setId(getNextId());
            }
            map.put(object.getId(), object);
        } else {
            throw new RuntimeException("Must not be null");
        }
        return object;
    }

    void deleteById(ID id) { map.remove(id);}
    void delete(T object) { map.entrySet().removeIf(entry -> entry.getValue().equals(object)); }

    private Long getNextId() {
        if(map.keySet().isEmpty()) {
            return 1L;
        }
        else {
            return Collections.max(map.keySet()) + 1;
        }
    }
}
