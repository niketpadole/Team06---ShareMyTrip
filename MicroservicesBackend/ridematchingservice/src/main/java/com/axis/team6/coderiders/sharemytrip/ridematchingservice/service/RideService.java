package com.axis.team6.coderiders.sharemytrip.ridematchingservice.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.BookRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.CreatePublisherRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.PublisherRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.RideDetailsDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PassengerRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PublisherRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.RideDetailsView;

public interface RideService {

	PublisherRide createPublisherRide(@Valid CreatePublisherRideDTO createPublisherRideDTO);

	PassengerRide bookRide(@Valid BookRideDTO bookRideDTO);

	List<PublisherRideDTO> viewAvailableRides();

	void cancelRide(Integer passengerRideId);

	void cancelPublishedRide(Integer publisherRideId);

	//List<PublisherRideDTO> getPassengerRides(int passengerId);
	   List<RideDetailsDTO> getPassengerRidesDetails(int passengerId);

	List<PublisherRideDTO> getFilteredRides(String fromLocation, String toLocation,Date dateOfJourney);
	
	void cancelRide(int passengerRideId);

	List<PublisherRideDTO> getPublisherRidesDetails(int id);

	List<PublisherRideDTO> getCompletedRidesDetails();

	List<PublisherRideDTO> getCompletedRides(@Valid Integer id);

	String setRideStatus(Integer id);

	List<PublisherRideDTO> getNotCompletedRides(@Valid Integer id);

	List<PublisherRideDTO> getOngoingRides(@Valid Integer id);

	List<RideDetailsDTO> viewPassengers(@Valid Integer id);

	List<RideDetailsDTO> viewPastRides(@Valid Integer id);

	List<RideDetailsDTO> getNotCompletedRidesPassenger(@Valid Integer passengerId);

	List<RideDetailsDTO> getOngoingRidesPassenger(@Valid Integer passengerId);

	String setRideStatusPassenger(Integer passengerRideId);

	String setRideStatusEnd(Integer id);
}
