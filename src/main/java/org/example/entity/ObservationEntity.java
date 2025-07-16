package org.example.entity;

import javax.persistence.*;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public void setObserverName(String observerName) {
        this.observerName = observerName;
    }
}
