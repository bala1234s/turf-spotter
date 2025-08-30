package com.stackqueue.turfSpotter.Controller;

import com.stackqueue.turfSpotter.Service.TurfDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/turf-details")
@AllArgsConstructor
public class TurfDetailsController {

    private final TurfDetailsService turfDetailsService;

    

}
