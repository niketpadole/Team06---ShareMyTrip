package com.axis.team6.coderiders.sharemytrip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.team6.coderiders.sharemytrip.entity.Passenger;
import com.axis.team6.coderiders.sharemytrip.exception.PassengerNotFoundException;
import com.axis.team6.coderiders.sharemytrip.repository.PassengerRepository;
import com.axis.team6.coderiders.sharemytrip.utils.PassengerRegistrationRequest;

@Service
public class PassengerServiceImpl implements PassengerService{
	    @Autowired
	    private PassengerRepository passengerRepository;

	    public Passenger registerPassenger(PassengerRegistrationRequest registrationRequest) {
	        Passenger passenger = new Passenger();
	        passenger.setFirstName(registrationRequest.getFirstName());
	        passenger.setLastName(registrationRequest.getLastName());
	        passenger.setMobile(registrationRequest.getMobile());
	        passenger.setDateOfBirth(registrationRequest.getDateOfBirth());
	        passenger.setEmail(registrationRequest.getEmail());
	        passenger.setAadharCard(registrationRequest.getAadharCard());
	        passenger.setMiniBio(registrationRequest.getMiniBio());
	        passenger.setPassword(registrationRequest.getPassword());
	        // Set timestamp
	        passenger.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
	        return passengerRepository.save(passenger);
	    }

	    public Passenger loginPassenger(String email, String password) {
	        Passenger passenger = passengerRepository.findByEmailAndPassword(email, password);
	        if (passenger == null) {
	            throw new PassengerNotFoundException("Invalid email or password");
	        }
	        return passenger;
	    }

	    public List<Passenger> getAllPassengers() {
	        return passengerRepository.findAll();
	    }

	    public Passenger getPassengerById(int id) {
	        return passengerRepository.findById(id)
	                .orElseThrow(() -> new PassengerNotFoundException("Passenger not found with id: " + id));
	    }

	    public Passenger updatePassenger(int id, Passenger passengerDetails) {
	        Passenger passenger = getPassengerById(id);
	        passenger.setFirstName(passengerDetails.getFirstName());
	        passenger.setLastName(passengerDetails.getLastName());
	        passenger.setMobile(passengerDetails.getMobile());
	        passenger.setDateOfBirth(passengerDetails.getDateOfBirth());
	        passenger.setEmail(passengerDetails.getEmail());
	        passenger.setAadharCard(passengerDetails.getAadharCard());
	        passenger.setMiniBio(passengerDetails.getMiniBio());
	        passenger.setPassword(passengerDetails.getPassword());
	        // Set timestamp
	        passenger.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
	        return passengerRepository.save(passenger);
	    }

	    public void deletePassenger(int id) {
	        passengerRepository.deleteById(id);
	    }


}
