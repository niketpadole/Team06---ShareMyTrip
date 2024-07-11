package com.axis.team6.coderiders.sharemytrip.farecalculationservice.service;

import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.DistanceDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.FareCalculationRequestDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.FareCalculationResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FareCalculationServiceTest {

    @Mock
    private FareCalculationService fareCalculationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateFare() {
        FareCalculationRequestDTO requestDTO = new FareCalculationRequestDTO();
        requestDTO.setFromLocation("New York");
        requestDTO.setToLocation("Los Angeles");

        FareCalculationResponseDTO responseDTO = new FareCalculationResponseDTO();
        responseDTO.setTotalFare(200.0f);

        when(fareCalculationService.calculateFare(any(FareCalculationRequestDTO.class))).thenReturn(responseDTO);

        FareCalculationResponseDTO result = fareCalculationService.calculateFare(requestDTO);
        assertEquals(200.0f, result.getTotalFare());

        verify(fareCalculationService, times(1)).calculateFare(any(FareCalculationRequestDTO.class));
    }

    @Test
    void calculateDistance() {
        FareCalculationRequestDTO requestDTO = new FareCalculationRequestDTO();
        requestDTO.setFromLocation("San Francisco");
        requestDTO.setToLocation("Chicago");

        DistanceDTO distanceDTO = new DistanceDTO();
        distanceDTO.setDistance(500.0f);

        when(fareCalculationService.calculateDistance(any(FareCalculationRequestDTO.class))).thenReturn(distanceDTO);

        DistanceDTO result = fareCalculationService.calculateDistance(requestDTO);
        assertEquals(500.0f, result.getDistance());

        verify(fareCalculationService, times(1)).calculateDistance(any(FareCalculationRequestDTO.class));
    }

    @Test
    void calculateJourneyTime() {
        FareCalculationRequestDTO requestDTO = new FareCalculationRequestDTO();
        requestDTO.setFromLocation("Miami");
        requestDTO.setToLocation("Orlando");

        when(fareCalculationService.calculateJourneyTime(any(FareCalculationRequestDTO.class))).thenReturn("03:00:00");

        String result = fareCalculationService.calculateJourneyTime(requestDTO);
        assertEquals("03:00:00", result);

        verify(fareCalculationService, times(1)).calculateJourneyTime(any(FareCalculationRequestDTO.class));
    }
}
