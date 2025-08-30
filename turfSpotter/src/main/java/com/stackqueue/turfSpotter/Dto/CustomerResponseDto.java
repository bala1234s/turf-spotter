package com.stackqueue.turfSpotter.Dto;

import com.stackqueue.turfSpotter.Enum.UserStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerResponseDto {
    private int customerId;
    private String customerName;
    private String customerPhoneNumber;
    private LocalDate accountRecentlyUpdateDate;
    private String customerImageUrl;
    private UserStatus customerStatus;
    private boolean isCustomerActive;

    private UserResponseDto userResponse;
}
