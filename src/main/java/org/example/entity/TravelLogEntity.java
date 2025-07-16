package org.example.entity;

import javax.persistence.*;

import org.example.enums.TravelMode;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
