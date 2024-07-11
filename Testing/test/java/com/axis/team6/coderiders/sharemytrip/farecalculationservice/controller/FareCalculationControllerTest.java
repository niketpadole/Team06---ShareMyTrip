package com.axis.team6.coderiders.sharemytrip.farecalculationservice.controller;

import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.DistanceDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.FareCalculationRequestDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.FareCalculationResponseDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.exception.InvalidLocationException;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.service.FareCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FareCalculationControllerTest {

    @Mock
    private FareCalculationService fareCalculationService;

    @InjectMocks
    private FareCalculationController fareCalculationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateFare() {
        FareCalculationRequestDTO requestDTO = new FareCalculationRequestDTO();
        FareCalculationResponseDTO responseDTO = new FareCalculationResponseDTO();
        when(fareCalculationService.calculateFare(any(FareCalculationRequestDTO.class))).thenReturn(responseDTO);

        ResponseEntity<FareCalculationResponseDTO> responseEntity = fareCalculationController.calculateFare(requestDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody());

        verify(fareCalculationService, times(1)).calculateFare(any(FareCalculationRequestDTO.class));
    }

    @Test
    void calculateDistance() {
        FareCalculationRequestDTO requestDTO = new FareCalculationRequestDTO();
        DistanceDTO distanceDTO = new DistanceDTO();
        when(fareCalculationService.calculateDistance(any(FareCalculationRequestDTO.class))).thenReturn(distanceDTO);

        ResponseEntity<DistanceDTO> responseEntity = fareCalculationController.calculateDistance(requestDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(distanceDTO, responseEntity.getBody());

        verify(fareCalculationService, times(1)).calculateDistance(any(FareCalculationRequestDTO.class));
    }

//    @Test
//    void calculateJourneyTime() {
//        FareCalculationRequestDTO requestDTO = new FareCalculationRequestDTO();
//        Time journeyTime = Time.valueOf("02:00:00");
//        when(fareCalculationService.calculateJourneyTime(any(FareCalculationRequestDTO.class))).thenReturn(String.valueOf(journeyTime.toLocalTime()));
//
//        Time result = fareCalculationController.calculateJourneyTime(requestDTO);
//        assertEquals(journeyTime, result);
//
//        verify(fareCalculationService, times(1)).calculateJourneyTime(any(FareCalculationRequestDTO.class));
//    }

    @Test
    void handleInvalidLocationException() {
        InvalidLocationException exception = new InvalidLocationException("Invalid location");
        ResponseEntity<String> responseEntity = fareCalculationController.handleInvalidLocationException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid location", responseEntity.getBody());
    }
}
