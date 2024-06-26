package com.axis.team6.coderiders.sharemytrip.farecalculationservice.service;

import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.DistanceDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.FareCalculationRequestDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.FareCalculationResponseDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.entity.Distance;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.repository.DistanceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FareCalculationServiceImpl implements FareCalculationService {

    @Autowired
    private DistanceRepository distanceRepository;

    @Autowired
    private GoogleMapsService googleMapsService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FareCalculationResponseDTO calculateFare(FareCalculationRequestDTO requestDTO) {
        Distance distance = distanceRepository.findByFromLocationAndToLocation(
                requestDTO.getFromLocation(), requestDTO.getToLocation());

        float distanceInKm;
        if (distance == null) {
            try {
                distanceInKm = googleMapsService.getDistance(requestDTO.getFromLocation(), requestDTO.getToLocation());
            } catch (IOException e) {
                throw new RuntimeException("Error getting distance from Google Maps API", e);
            }

            distance = new Distance();
            distance.setFromLocation(requestDTO.getFromLocation());
            distance.setToLocation(requestDTO.getToLocation());
            distance.setDistance(distanceInKm);
            //distance.setFarePerSeats(1.5f); // Set a default fare per seat or retrieve from some configuration
            distanceRepository.save(distance);
        } else {
            distanceInKm = distance.getDistance();
        }

        float totalFare;
        int baseFare=50;
        float farePerKm;
        if(distanceInKm < 100){
            farePerKm=5.5f;
        }
        else if(distanceInKm < 200){
            farePerKm=4.0f;
        }
        else if(distanceInKm < 500){
            farePerKm=3.8f;
        }
        else{
            farePerKm=2.6f;
        }
        totalFare = (distanceInKm*farePerKm)+baseFare;
        FareCalculationResponseDTO responseDTO = new FareCalculationResponseDTO();
        responseDTO.setTotalFare(totalFare);

        return responseDTO;
    }

    @Override
    public DistanceDTO calculateDistance(FareCalculationRequestDTO requestDTO) {
        Distance distance = distanceRepository.findByFromLocationAndToLocation(
                requestDTO.getFromLocation(), requestDTO.getToLocation());

        float distanceInKm;
        if (distance == null) {
            try {
                distanceInKm = googleMapsService.getDistance(requestDTO.getFromLocation(), requestDTO.getToLocation());
            } catch (IOException e) {
                throw new RuntimeException("Error getting distance from Google Maps API", e);
            }

            distance = new Distance();
            distance.setFromLocation(requestDTO.getFromLocation());
            distance.setToLocation(requestDTO.getToLocation());
            distance.setDistance(distanceInKm);
            distanceRepository.save(distance);
        } else {
            distanceInKm = distance.getDistance();
        }

        DistanceDTO distanceDTO = new DistanceDTO();
        distanceDTO.setDistance(distanceInKm);

        return distanceDTO;
    }

    @Override
    public String calculateJourneyTime(FareCalculationRequestDTO requestDTO) {
        try {
            String fromCoordinates = googleMapsService.getCoordinates(requestDTO.getFromLocation());
            String toCoordinates = googleMapsService.getCoordinates(requestDTO.getToLocation());
            return googleMapsService.getDuration(fromCoordinates, toCoordinates);
        } catch (IOException e) {
            throw new RuntimeException("Error getting journey time from Google Maps API", e);
        }
    }
}
