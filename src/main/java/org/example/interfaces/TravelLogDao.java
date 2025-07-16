package org.example.interfaces;

import org.example.entity.TravelLogEntity;
import org.example.enums.TravelMode;

import java.util.List;

public interface TravelLogDao extends GenericDao<TravelLogEntity> {
    List<TravelLogEntity> findByMode(TravelMode mode);
}
