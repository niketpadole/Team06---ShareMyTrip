package com.axis.usermanagementservice.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.axis.usermanagementservice.dto.CreatePassengerRideDTO;
import com.axis.usermanagementservice.dto.PassengerDTO;
import com.axis.usermanagementservice.dto.PassengerRegistrationRequest;
import com.axis.usermanagementservice.dto.PublisherRideDTO;
import com.axis.usermanagementservice.dto.RideDetailsDTO;
import com.axis.usermanagementservice.dto.TransactionLinkRequestDto;
import com.axis.usermanagementservice.entity.Passenger;

@Service
public interface PassengerService {
    ResponseEntity<?> registerPassenger(PassengerRegistrationRequest registrationRequest);

    PassengerDTO loginPassenger(String email, String password);

    List<PassengerDTO> getAllPassengers();

    Passenger getPassengerById(int id);

    Passenger updatePassenger(int id, Passenger passengerDetails);

    void deletePassenger(int id);
    
    String bookRide(CreatePassengerRideDTO createPassengerRideDTO);
    
    List<PublisherRideDTO> getPassengerRides(int passengerId);
    
    List<PublisherRideDTO> getFilteredRides(String fromLocation, String toLocation,String dateStr);
    
    void cancelBooking(int passengerRideId);

	PassengerDTO getPassengerByEmail(String email);

	List<RideDetailsDTO> viewRidesById(Integer passengerId);

	List<RideDetailsDTO> viewNotCompletedRides(int id);

	List<RideDetailsDTO> viewOngoingRidesPassenger(int id);

	//String payRide(RideDetailsDTO rideDetails, Integer passengerRideId);

	//String payRide(RideDetailsDTO rideDetails);

	String payRide(TransactionLinkRequestDto rideDetails);

	String setRideStatus(Integer integer);
}
