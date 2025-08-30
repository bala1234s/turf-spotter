package com.stackqueue.turfSpotter.Dto;

import com.stackqueue.turfSpotter.Enum.UserStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TurfOwnerResponseDto {
    private int turfOwnerId;
    private String turfOwnerName;
    private String turfOwnerPhoneNumber;
    private boolean isTurfOwnerActive;
    private LocalDate accountRecentlyUpdateDate;
    private String turfOwnerImageUrl;
    private UserStatus turfOwnerStatus;

    private UserResponseDto userResponseDto;
}
