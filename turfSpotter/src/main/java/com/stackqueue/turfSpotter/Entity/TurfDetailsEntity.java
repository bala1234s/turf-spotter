package com.stackqueue.turfSpotter.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Table(name = "turf_details")
public class TurfDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int turfId;
    private String turfName;
    private String turfAddress;
    private String turfLocation;
    private double turfPrice;
    private boolean isTurfActive;
    private int capacity;
    private String bookingPolicy;
    private LocalDateTime turfCreatedAt;
    private LocalDateTime turfUpdatedAt;



    @ManyToOne
    private TurfOwnerEntity turfOwnerEntity;

}

