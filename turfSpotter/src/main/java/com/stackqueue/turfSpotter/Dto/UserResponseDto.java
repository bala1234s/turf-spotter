package com.stackqueue.turfSpotter.Dto;

import com.stackqueue.turfSpotter.Enum.UserRole;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponseDto {
    private String username;
    private UserRole userRole;
    private LocalDate accountCreatedAt;

}
