package com.axis.team6.coderiders.sharemytrip.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.axis.team6.coderiders.sharemytrip.entity.Passenger;
import com.axis.team6.coderiders.sharemytrip.utils.PassengerRegistrationRequest;
@Service
public interface PassengerService {
    public Passenger registerPassenger(PassengerRegistrationRequest registrationRequest);

    public Passenger loginPassenger(String email, String password);

    public List<Passenger> getAllPassengers();

    public Passenger getPassengerById(int id);
    public Passenger updatePassenger(int id, Passenger passengerDetails);

    public void deletePassenger(int id);
}
