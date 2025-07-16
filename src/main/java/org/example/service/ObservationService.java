package org.example.service;

import java.time.LocalDate;
import java.util.List;

import org.example.entity.ObservationEntity;
import org.example.entity.SpecieEntity;
import org.example.interfaces.ObservationDao;

public class ObservationService {

    private final ObservationDao observationDao;

    public ObservationService(ObservationDao observationDao) {
        this.observationDao = observationDao;
    }

    public ObservationEntity createObservation(SpecieEntity specie, String observerName, String location,
                                               double latitude, double longitude, String comment) {
        ObservationEntity obs = new ObservationEntity();
        obs.setSpecie(specie);
        obs.setObserverName(observerName);
        obs.setLocation(location);
        obs.setLatitude(latitude);
        obs.setLongitude(longitude);
        obs.setObservationDate(LocalDate.now());
        obs.setComment(comment);
        observationDao.save(obs);
        return obs;
    }

    public List<ObservationEntity> findAll() {
        return observationDao.findAll();
    }
}
