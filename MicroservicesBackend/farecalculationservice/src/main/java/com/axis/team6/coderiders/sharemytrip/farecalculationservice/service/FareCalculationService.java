package com.axis.team6.coderiders.sharemytrip.farecalculationservice.service;

import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.DistanceDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.FareCalculationRequestDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.FareCalculationResponseDTO;

public interface FareCalculationService {
	FareCalculationResponseDTO calculateFare(FareCalculationRequestDTO requestDTO);
	DistanceDTO calculateDistance(FareCalculationRequestDTO requestDTO);
	String calculateJourneyTime(FareCalculationRequestDTO requestDTO);
}
