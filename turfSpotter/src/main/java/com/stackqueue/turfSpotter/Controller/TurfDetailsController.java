package com.stackqueue.turfSpotter.Controller;

import com.stackqueue.turfSpotter.Dto.TurfDetailsRequestDto;
import com.stackqueue.turfSpotter.Dto.TurfDetailsResponseDto;
import com.stackqueue.turfSpotter.Service.TurfDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turf-details")
@AllArgsConstructor
public class TurfDetailsController {

    private final TurfDetailsService turfDetailsService;

    @PostMapping("{turfOwnerId}")
    public ResponseEntity<?> createTurf(@PathVariable int turfOwnerId,@RequestBody TurfDetailsRequestDto turfDetailsRequestDto){
        return turfDetailsService.createTurf(turfOwnerId,turfDetailsRequestDto);

    }

    @GetMapping
    public List<TurfDetailsResponseDto> getAllTurfList(){
        return turfDetailsService.getAllTurfList();

    }

}
