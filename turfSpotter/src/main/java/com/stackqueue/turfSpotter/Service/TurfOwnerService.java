package com.stackqueue.turfSpotter.Service;

import com.stackqueue.turfSpotter.Dto.TurfOwnerRequestDto;
import com.stackqueue.turfSpotter.Dto.TurfOwnerResponseDto;
import com.stackqueue.turfSpotter.Dto.UserResponseDto;
import com.stackqueue.turfSpotter.Entity.TurfOwnerEntity;
import com.stackqueue.turfSpotter.Entity.UserEntity;
import com.stackqueue.turfSpotter.Enum.UserRole;
import com.stackqueue.turfSpotter.Enum.UserStatus;
import com.stackqueue.turfSpotter.POJO.ServerResponse;
import com.stackqueue.turfSpotter.POJO.TurfOwnerException;
import com.stackqueue.turfSpotter.Repository.TurfOwnerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TurfOwnerService {

    @Autowired
    private TurfOwnerRepository turfOwnerRepository;

    @Transactional
    public ResponseEntity<?> createTurfOwner(TurfOwnerRequestDto turfOwnerDto) {
        if(!turfOwnerRepository.existsByUserEntityUsername(turfOwnerDto.getUserRequestDto().getUsername())) {
            UserEntity user = UserEntity.builder()
                    .username(turfOwnerDto.getUserRequestDto().getUsername())
                    .userPassword(turfOwnerDto.getUserRequestDto().getUserPassword())
                    .userRole(UserRole.TURFOWNER)
                    .accountCreatedAt(LocalDate.now())
                    .build();
            TurfOwnerEntity turfOwnerEntity = TurfOwnerEntity.builder()
                    .turfOwnerName(turfOwnerDto.getTurfOwnerName())
                    .turfOwnerPhoneNumber(turfOwnerDto.getTurfOwnerPhoneNumber())
                    .turfOwnerStatus(UserStatus.OFFLINE)
                    .turfOwnerImageUrl(turfOwnerDto.getTurfOwnerImageUrl())
                    .isTurfOwnerActive(true)
                    .accountRecentlyUpdateDate(LocalDate.now())
                    .userEntity(user)
                    .build();

            turfOwnerRepository.save(turfOwnerEntity);

            return new ResponseEntity<>(new ServerResponse(HttpStatus.CREATED, "Turf Owner is Created Successfully!!! ", LocalDate.now()), HttpStatus.CREATED);

        }

        throw new TurfOwnerException(HttpStatus.CONFLICT, "This Turf Owner is already Exist!!!", LocalDate.now());

    }

    public List<TurfOwnerResponseDto> getAllTurfOwners() {
        List<TurfOwnerResponseDto> turfOwnerDtoList = turfOwnerRepository.findAll()
                .stream().map(this::mapToTurfOwnerDto).toList();

        return turfOwnerDtoList;
    }

    private TurfOwnerResponseDto mapToTurfOwnerDto(TurfOwnerEntity turfOwnerEntity) {
        UserResponseDto userResponse = new UserResponseDto();

        userResponse.setUsername(turfOwnerEntity.getUserEntity().getUsername());
        userResponse.setUserRole(turfOwnerEntity.getUserEntity().getUserRole());
        userResponse.setAccountCreatedAt(turfOwnerEntity.getUserEntity().getAccountCreatedAt());

        TurfOwnerResponseDto turfOwnerResponseDto = new TurfOwnerResponseDto();

        turfOwnerResponseDto.setTurfOwnerId(turfOwnerEntity.getTurfOwnerId());
        turfOwnerResponseDto.setTurfOwnerName(turfOwnerEntity.getTurfOwnerName());
        turfOwnerResponseDto.setTurfOwnerPhoneNumber(turfOwnerEntity.getTurfOwnerPhoneNumber());
        turfOwnerResponseDto.setTurfOwnerImageUrl(turfOwnerEntity.getTurfOwnerImageUrl());
        turfOwnerResponseDto.setTurfOwnerActive(turfOwnerEntity.isTurfOwnerActive());
        turfOwnerResponseDto.setTurfOwnerStatus(turfOwnerEntity.getTurfOwnerStatus());
        turfOwnerResponseDto.setAccountRecentlyUpdateDate(turfOwnerEntity.getAccountRecentlyUpdateDate());
        turfOwnerResponseDto.setUserResponseDto(userResponse);

        return turfOwnerResponseDto;

    }
}
