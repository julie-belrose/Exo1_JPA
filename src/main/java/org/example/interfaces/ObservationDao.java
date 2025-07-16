package org.example.interfaces;

import org.example.entity.ObservationEntity;
import java.util.List;

public interface ObservationDao extends GenericDao<ObservationEntity> {
    List<ObservationEntity> findBySpecieId(Long specieId);

    List<ObservationEntity> findByLocation(String location);
}