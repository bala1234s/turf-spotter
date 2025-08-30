package com.stackqueue.turfSpotter.Entity;

import com.stackqueue.turfSpotter.Enum.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter

@Table(name = "customer_info")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String customerName;
    private String customerPhoneNumber;
    private LocalDate accountRecentlyUpdateDate;
    private String customerImageUrl;
    private UserStatus customerStatus;
    private boolean isCustomerActive;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity userEntity;
}
