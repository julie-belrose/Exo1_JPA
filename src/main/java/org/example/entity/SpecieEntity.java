package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import org.example.enums.Category;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpecieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commonName;
    private String scientificName;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToMany(mappedBy = "species")
    private final List<RegionEntity> regions = new ArrayList<>();

    @OneToMany(mappedBy = "specie", cascade = CascadeType.ALL)
    private List<ObservationEntity> observations = new ArrayList<>();

    @Override
    public String toString() {
        return "SpecieEntity{" +
                "id=" + id +
                ", commonName='" + commonName + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", category=" + category +
                '}';
    }
}
