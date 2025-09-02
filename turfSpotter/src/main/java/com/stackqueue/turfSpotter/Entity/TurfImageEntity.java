package com.stackqueue.turfSpotter.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "turf_images")
public class TurfImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

    private String imageUrl;
    private String caption;

    @ManyToOne(fetch = FetchType.LAZY)
    private TurfDetailsEntity turfDetailsEntity;
}

