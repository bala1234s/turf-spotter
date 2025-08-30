package com.stackqueue.turfSpotter.Dto;

import com.stackqueue.turfSpotter.Enum.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private String username;
    private String userPassword;
//    private UserRole userRole;

}
