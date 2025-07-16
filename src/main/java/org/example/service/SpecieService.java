package org.example.service;

import java.util.List;

import org.example.entity.SpecieEntity;
import org.example.enums.Category;
import org.example.interfaces.SpecieDao;

public class SpecieService {

    private final SpecieDao specieDao;

    public SpecieService(SpecieDao specieDao) {
        this.specieDao = specieDao;
    }

    public SpecieEntity createSpecie(String commonName, String scientificName, Category category) {
        SpecieEntity specie = new SpecieEntity();
        specie.setCommonName(commonName);
        specie.setScientificName(scientificName);
        specie.setCategory(category);
        specieDao.save(specie);
        return specie;
    }

    public List<SpecieEntity> findAll() {
        return specieDao.findAll();
    }
}
