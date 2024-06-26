package com.axis.usermanagementservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.axis.usermanagementservice.dto.CreatePassengerRideDTO;
import com.axis.usermanagementservice.dto.PassengerRegistrationRequest;
import com.axis.usermanagementservice.dto.PublisherRideDTO;
import com.axis.usermanagementservice.entity.Passenger;

@Service
public interface PassengerService {
    Passenger registerPassenger(PassengerRegistrationRequest registrationRequest);

    Passenger loginPassenger(String email, String password);

    List<Passenger> getAllPassengers();

    Passenger getPassengerById(int id);

    Passenger updatePassenger(int id, Passenger passengerDetails);

    void deletePassenger(int id);
    
    String bookRide(CreatePassengerRideDTO createPassengerRideDTO);
    
    List<PublisherRideDTO> getPassengerRides(int passengerId);
    
    List<PublisherRideDTO> getFilteredRides(String fromLocation, String toLocation);
    
    void cancelBooking(int passengerRideId);
}
