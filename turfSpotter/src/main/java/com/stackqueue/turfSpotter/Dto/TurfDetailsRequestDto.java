package com.stackqueue.turfSpotter.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TurfDetailsRequestDto {
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

    private TurfOwnerRequestDto turfOwnerRequestDto;
}
