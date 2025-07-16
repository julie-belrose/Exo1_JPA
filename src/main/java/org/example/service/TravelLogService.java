package org.example.service;

import java.util.List;

import org.example.entity.ObservationEntity;
import org.example.entity.TravelLogEntity;
import org.example.enums.TravelMode;
import org.example.interfaces.TravelLogDao;

public class TravelLogService {

    private final TravelLogDao travelLogDao;

    public TravelLogService(TravelLogDao travelLogDao) {
        this.travelLogDao = travelLogDao;
    }

    public TravelLogEntity createTravelLog(ObservationEntity observation, double distanceKm, TravelMode mode) {
        TravelLogEntity log = new TravelLogEntity();
        log.setObservation(observation);
        log.setDistanceKm(distanceKm);
        log.setMode(mode);
        travelLogDao.save(log);
        return log;
    }

    public List<TravelLogEntity> findAll() {
        return travelLogDao.findAll();
    }
}
