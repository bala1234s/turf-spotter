package com.stackqueue.turfSpotter.Controller;

import com.stackqueue.turfSpotter.Dto.TurfOwnerRequestDto;
import com.stackqueue.turfSpotter.Dto.TurfOwnerResponseDto;
import com.stackqueue.turfSpotter.Service.TurfOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turf-owner")
@AllArgsConstructor
public class TurfOwnerController {

    private final TurfOwnerService turfOwnerService;

    @PostMapping
    public ResponseEntity<?> createTurfOwner(@RequestBody TurfOwnerRequestDto turfOwnerDto){
        return turfOwnerService.createTurfOwner(turfOwnerDto);
    }

    @GetMapping
    public List<TurfOwnerResponseDto> getAllTurfOwners(){
        return turfOwnerService.getAllTurfOwners();
    }

}
