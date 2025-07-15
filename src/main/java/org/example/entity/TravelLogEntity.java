package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import org.example.enums.TravelMode;
import org.example.entity.ObservationEntity;


public class TravelLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "observation_id")
    private ObservationEntity observation;

    private Double distanceKm;

    @Enumerated(EnumType.STRING)
    private TravelMode mode;
}
