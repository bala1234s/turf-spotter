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
@Table(name="turf_owner_info")
public class TurfOwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int turfOwnerId;
    private String turfOwnerName;
    private String turfOwnerPhoneNumber;
    private boolean isTurfOwnerActive;
    private LocalDate accountRecentlyUpdateDate;
    private String turfOwnerImageUrl;
    private UserStatus turfOwnerStatus;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity userEntity;


}
