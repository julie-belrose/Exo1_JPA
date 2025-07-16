package org.example.service;

import org.example.interfaces.RegionDao;
import org.example.entity.RegionEntity;
import org.example.enums.Climate;

public class RegionService {

    private final RegionDao regionDao;

    public RegionService(RegionDao regionDao) {
        this.regionDao = regionDao;
    }

    public RegionEntity createRegion(String name, double surface, Climate climate) {
        RegionEntity region = new RegionEntity();
        region.setName(name);
        region.setSurface(surface);
        region.setClimate(climate);
        regionDao.create(region);
        return region;
    }
}
