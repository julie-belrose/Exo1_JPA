package org.example.entity;

import javax.persistence.*;
import java.time.LocalDate;

import org.example.entity.TravelLogEntity;
import org.example.entity.SpecieEntity;


public class ObservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SpecieEntity specie;

    private String observerName;
    private String location;
    private Double latitude;
    private Double longitude;
    private LocalDate observationDate;
    private String comment;

    @OneToOne(mappedBy = "observation", cascade = CascadeType.ALL)
    private TravelLogEntity travelLog;
}
