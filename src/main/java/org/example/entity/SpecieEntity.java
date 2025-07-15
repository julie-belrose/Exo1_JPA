package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import org.example.enums.Category;


public class SpecieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commonName;
    private String scientificName;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToMany(mappedBy = "species")
    private final List<RegionEntity> regions = new ArrayList<>();{

    }
}
