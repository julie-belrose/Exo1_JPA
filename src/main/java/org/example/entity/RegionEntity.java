package org.example.entity;

import lombok.*;
import org.example.enums.Climate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private double surface;

    @Enumerated(EnumType.STRING)
    private Climate climate;

    @ManyToMany
    private List<SpecieEntity> species = new ArrayList<>();

}
