package com.stackqueue.turfSpotter.Dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TurfDetailsResponseDto {
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
    private String turfSurface;
    private String turfSize;
    private boolean parkingAvailable;
    private boolean waterAvailable;
    private boolean changingRoomsAvailable;
    private boolean washRoomAvailable;


    private TurfOwnerResponseDto turfOwnerResponseDto;
    private List<TurfTypeDto> turfTypeDtos;
    private List<TurfImageResponseDto> turfImageResponsesDto;

}
