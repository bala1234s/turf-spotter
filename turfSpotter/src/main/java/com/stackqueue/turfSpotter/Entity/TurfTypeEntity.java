package com.stackqueue.turfSpotter.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "turf_type_entity")
public class TurfTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int turfTypeId;
    private String turfTypeName;

    @ManyToOne
    private TurfDetailsEntity turfDetailsEntity;
}
