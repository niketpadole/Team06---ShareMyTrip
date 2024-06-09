package com.axis.team6.coderiders.sharemytrip.ridematchingservice.service;

import java.util.List;

import javax.validation.Valid;

import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.BookRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.CreatePublisherRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.PublisherRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PassengerRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PublisherRide;

public interface RideService {

	PublisherRide createPublisherRide(@Valid CreatePublisherRideDTO createPublisherRideDTO);

	PassengerRide bookRide(@Valid BookRideDTO bookRideDTO);

	List<PublisherRide> viewAvailableRides();

	void cancelRide(Integer passengerRideId);

	void cancelPublishedRide(Integer publisherRideId);

	List<PublisherRideDTO> getPassengerRides(int passengerId);

	List<PublisherRideDTO> getFilteredRides(String fromLocation, String toLocation);
}
