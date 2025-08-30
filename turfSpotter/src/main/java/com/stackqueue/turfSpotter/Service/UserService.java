package com.stackqueue.turfSpotter.Service;

import com.stackqueue.turfSpotter.Dto.UserRequestDto;
import com.stackqueue.turfSpotter.Entity.UserEntity;
import com.stackqueue.turfSpotter.Enum.UserRole;
import com.stackqueue.turfSpotter.POJO.UserException;
import com.stackqueue.turfSpotter.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<?> createUser(UserRequestDto userDto) {
        UserEntity user = UserEntity.builder()
                .username(userDto.getUsername())
                .userPassword(userDto.getUserPassword())
                .userRole(UserRole.CUSTOMER)
                .accountCreatedAt(LocalDate.now())
                .build();

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getUser(int userID) {
        UserEntity user = userRepository.findById(userID)
                .orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND,"This userID is NotFound", LocalDate.now()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
