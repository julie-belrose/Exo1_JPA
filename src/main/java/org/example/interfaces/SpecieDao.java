package org.example.interfaces;

import org.example.entity.SpecieEntity;
import org.example.enums.Category;

import java.util.List;

public interface SpecieDao extends GenericDao<SpecieEntity> {
    List<SpecieEntity> findByCategory(Category category);
}
