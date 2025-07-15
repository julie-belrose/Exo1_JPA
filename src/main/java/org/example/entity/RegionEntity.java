package org.example.entity;

import lombok.*;
import org.example.enums.Climate;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "region")
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private double surface;

    @Enumerated(EnumType.STRING)
    private Climate climate;
}
