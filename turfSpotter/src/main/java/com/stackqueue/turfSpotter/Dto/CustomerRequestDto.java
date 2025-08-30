package com.stackqueue.turfSpotter.Dto;

import com.stackqueue.turfSpotter.Enum.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {
    private int customerId;
    private String customerName;
    private String customerPhoneNumber;
    private LocalDate accountRecentlyUpdateDate;
    private String customerImageUrl;
    private UserStatus customerStatus;
    private boolean isCustomerActive;

    private UserRequestDto userDto;
}
