package com.axis.team6.coderiders.sharemytrip.farecalculationservice.service;

import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.DistanceDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.FareCalculationRequestDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.FareCalculationResponseDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.entity.Distance;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.repository.DistanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FareCalculationServiceImplTest {

    @Mock
    private DistanceRepository distanceRepository;

    @Mock
    private GoogleMapsService googleMapsService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FareCalculationServiceImpl fareCalculationServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateFare() throws IOException {
        FareCalculationRequestDTO requestDTO = new FareCalculationRequestDTO();
        requestDTO.setFromLocation("New York");
        requestDTO.setToLocation("Los Angeles");

        Distance distance = new Distance();
        distance.setFromLocation("New York");
        distance.setToLocation("Los Angeles");
        distance.setDistance(450.0f);

        when(distanceRepository.findByFromLocationAndToLocation(any(String.class), any(String.class))).thenReturn(distance);

        FareCalculationResponseDTO responseDTO = fareCalculationServiceImpl.calculateFare(requestDTO);
        assertEquals(1760.0f, responseDTO.getTotalFare());

        verify(distanceRepository, times(1)).findByFromLocationAndToLocation(any(String.class), any(String.class));
    }

    @Test
    void calculateFareWithGoogleMapsService() throws IOException {
        FareCalculationRequestDTO requestDTO = new FareCalculationRequestDTO();
        requestDTO.setFromLocation("New York");
        requestDTO.setToLocation("Los Angeles");

        when(distanceRepository.findByFromLocationAndToLocation(any(String.class), any(String.class))).thenReturn(null);
        when(googleMapsService.getDistance(any(String.class), any(String.class))).thenReturn(450.0f);

        FareCalculationResponseDTO responseDTO = fareCalculationServiceImpl.calculateFare(requestDTO);
        assertEquals(1760.0f, responseDTO.getTotalFare());

        verify(distanceRepository, times(1)).findByFromLocationAndToLocation(any(String.class), any(String.class));
        verify(googleMapsService, times(1)).getDistance(any(String.class), any(String.class));
        verify(distanceRepository, times(1)).save(any(Distance.class));
    }

    @Test
    void calculateDistance() throws IOException {
        FareCalculationRequestDTO requestDTO = new FareCalculationRequestDTO();
        requestDTO.setFromLocation("San Francisco");
        requestDTO.setToLocation("Chicago");

        Distance distance = new Distance();
        distance.setFromLocation("San Francisco");
        distance.setToLocation("Chicago");
        distance.setDistance(500.0f);

        when(distanceRepository.findByFromLocationAndToLocation(any(String.class), any(String.class))).thenReturn(distance);

        DistanceDTO result = fareCalculationServiceImpl.calculateDistance(requestDTO);
        assertEquals(500.0f, result.getDistance());

        verify(distanceRepository, times(1)).findByFromLocationAndToLocation(any(String.class), any(String.class));
    }

    @Test
    void calculateDistanceWithGoogleMapsService() throws IOException {
        FareCalculationRequestDTO requestDTO = new FareCalculationRequestDTO();
        requestDTO.setFromLocation("San Francisco");
        requestDTO.setToLocation("Chicago");

        when(distanceRepository.findByFromLocationAndToLocation(any(String.class), any(String.class))).thenReturn(null);
        when(googleMapsService.getDistance(any(String.class), any(String.class))).thenReturn(500.0f);

        DistanceDTO result = fareCalculationServiceImpl.calculateDistance(requestDTO);
        assertEquals(500.0f, result.getDistance());

        verify(distanceRepository, times(1)).findByFromLocationAndToLocation(any(String.class), any(String.class));
        verify(googleMapsService, times(1)).getDistance(any(String.class), any(String.class));
        verify(distanceRepository, times(1)).save(any(Distance.class));
    }

    @Test
    void calculateJourneyTime() throws IOException {
        FareCalculationRequestDTO requestDTO = new FareCalculationRequestDTO();
        requestDTO.setFromLocation("Miami");
        requestDTO.setToLocation("Orlando");

        when(googleMapsService.getCoordinates(any(String.class))).thenReturn("25.7617,-80.1918").thenReturn("28.5383,-81.3792");
        when(googleMapsService.getDuration(any(String.class), any(String.class))).thenReturn("03:00:00");

        String result = fareCalculationServiceImpl.calculateJourneyTime(requestDTO);
        assertEquals("03:00:00", result);

        verify(googleMapsService, times(2)).getCoordinates(any(String.class));
        verify(googleMapsService, times(1)).getDuration(any(String.class), any(String.class));
    }
}
