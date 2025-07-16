package org.example.interfaces;

import org.example.entity.RegionEntity;

import java.util.List;

public interface RegionDao {
    void create(RegionEntity region);
    RegionEntity findById(Long id);
    List<RegionEntity> findAll();
    void update(RegionEntity region);
    void delete(Long id);
}
