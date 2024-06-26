package com.axis.team6.coderiders.sharemytrip.farecalculationservice.controller;


import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.DistanceDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.FareCalculationRequestDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.FareCalculationResponseDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.exception.InvalidLocationException;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.service.FareCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;

@RestController
@RequestMapping("/fare")
@CrossOrigin("*")
public class FareCalculationController {

    @Autowired
    private FareCalculationService fareCalculationService;

    @PostMapping("/calculate")
    public ResponseEntity<FareCalculationResponseDTO> calculateFare(@RequestBody FareCalculationRequestDTO requestDTO) {
        FareCalculationResponseDTO responseDTO = fareCalculationService.calculateFare(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
    @PostMapping("/distance")
    public ResponseEntity<DistanceDTO> calculateDistance(@RequestBody FareCalculationRequestDTO requestDTO) {
        DistanceDTO responseDTO=fareCalculationService.calculateDistance(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/calculateJourneyTime")
    public Time calculateJourneyTime(@RequestBody FareCalculationRequestDTO requestDTO) {
        return Time.valueOf(fareCalculationService.calculateJourneyTime(requestDTO));
    }
    @ExceptionHandler(InvalidLocationException.class)
    public ResponseEntity<String> handleInvalidLocationException(InvalidLocationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
