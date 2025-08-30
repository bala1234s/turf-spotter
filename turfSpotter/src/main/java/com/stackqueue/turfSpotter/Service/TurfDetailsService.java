package com.stackqueue.turfSpotter.Service;

import com.stackqueue.turfSpotter.Dto.TurfDetailsRequestDto;
import com.stackqueue.turfSpotter.Dto.TurfDetailsResponseDto;
import com.stackqueue.turfSpotter.Dto.TurfOwnerResponseDto;
import com.stackqueue.turfSpotter.Entity.TurfDetailsEntity;
import com.stackqueue.turfSpotter.Entity.TurfOwnerEntity;
import com.stackqueue.turfSpotter.POJO.ServerResponse;
import com.stackqueue.turfSpotter.POJO.TurfDetailsException;
import com.stackqueue.turfSpotter.Repository.TurfDetailsRepository;
import com.stackqueue.turfSpotter.Repository.TurfOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TurfDetailsService {

    @Autowired
    private TurfDetailsRepository turfDetailsRepository;

    @Autowired
    private TurfOwnerRepository turfOwnerRepository;

    public ResponseEntity<?> createTurf(int turfOwnerId, TurfDetailsRequestDto turfDetailsRequestDto) {
        TurfOwnerEntity turfOwnerEntity = turfOwnerRepository.findById(turfOwnerId)
                .orElseThrow(()-> new TurfDetailsException(HttpStatus.NOT_FOUND, "This Turf Owner is not Found", LocalDate.now()));

        TurfDetailsEntity turfDetails = TurfDetailsEntity.builder()
                .turfName(turfDetailsRequestDto.getTurfName())
                .turfAddress(turfDetailsRequestDto.getTurfAddress())
                .turfLocation(turfDetailsRequestDto.getTurfLocation())
                .turfPrice(turfDetailsRequestDto.getTurfPrice())
                .isTurfActive(false)
                .capacity(turfDetailsRequestDto.getCapacity())
                .bookingPolicy(turfDetailsRequestDto.getBookingPolicy())
                .turfCreatedAt(LocalDateTime.now())
                .turfUpdatedAt(LocalDateTime.now())
                .turfOwnerEntity(turfOwnerEntity)
                .build();

        turfDetailsRepository.save(turfDetails);

        return new ResponseEntity<>(new ServerResponse(HttpStatus.CREATED, "This Turf is Registered Successfully, Please wait for approval...", LocalDate.now()), HttpStatus.CREATED);
    }

    public List<TurfDetailsResponseDto> getAllTurfList() {

        return turfDetailsRepository.findAll()
                .stream()
                .map(this::mapToTurfDto)
                .toList();
    }

    private TurfDetailsResponseDto mapToTurfDto(TurfDetailsEntity turf) {
        TurfDetailsResponseDto turfDto = new TurfDetailsResponseDto();

        turfDto.setTurfId(turf.getTurfId());
        turfDto.setTurfName(turf.getTurfName());
        turfDto.setTurfAddress(turf.getTurfAddress());
        turfDto.setTurfLocation(turf.getTurfLocation());
        turfDto.setTurfPrice(turf.getTurfPrice());
        turfDto.setTurfActive(turf.isTurfActive());
        turfDto.setCapacity(turf.getCapacity());
        turfDto.setBookingPolicy(turf.getBookingPolicy());
        turfDto.setTurfCreatedAt(turf.getTurfCreatedAt());
        turfDto.setTurfUpdatedAt(turf.getTurfUpdatedAt());

        if (turf.getTurfOwnerEntity() != null) {
            TurfOwnerResponseDto ownerDto = new TurfOwnerResponseDto();
            ownerDto.setTurfOwnerId(turf.getTurfOwnerEntity().getTurfOwnerId());
            ownerDto.setTurfOwnerName(turf.getTurfOwnerEntity().getTurfOwnerName());
            ownerDto.setTurfOwnerPhoneNumber(turf.getTurfOwnerEntity().getTurfOwnerPhoneNumber());
            ownerDto.setTurfOwnerActive(turf.getTurfOwnerEntity().isTurfOwnerActive());
            ownerDto.setAccountRecentlyUpdateDate(turf.getTurfOwnerEntity().getAccountRecentlyUpdateDate());
            ownerDto.setTurfOwnerImageUrl(turf.getTurfOwnerEntity().getTurfOwnerImageUrl());
            ownerDto.setTurfOwnerStatus(turf.getTurfOwnerEntity().getTurfOwnerStatus());

            turfDto.setTurfOwnerResponseDto(ownerDto);
        }

        return turfDto;
    }
}
