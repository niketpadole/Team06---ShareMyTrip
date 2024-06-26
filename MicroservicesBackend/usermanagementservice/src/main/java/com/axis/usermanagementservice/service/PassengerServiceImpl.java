package com.axis.usermanagementservice.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.axis.usermanagementservice.dto.CreatePassengerRideDTO;
import com.axis.usermanagementservice.dto.PassengerRegistrationRequest;
import com.axis.usermanagementservice.dto.PublisherRideDTO;
import com.axis.usermanagementservice.entity.Passenger;
import com.axis.usermanagementservice.exception.PassengerNotFoundException;
import com.axis.usermanagementservice.repository.PassengerRepository;
import com.axis.usermanagementservice.repository.PublisherRepository;

@Service
public class PassengerServiceImpl implements PassengerService {

	private static final String RIDE_MATCHING_SERVICE_URL = "http://localhost:8090/rides";
    @Autowired
    private PassengerRepository passengerRepository;
    
    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    public Passenger registerPassenger(PassengerRegistrationRequest registrationRequest) {
        Passenger passenger = modelMapper.map(registrationRequest, Passenger.class);
        passenger.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        passenger.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return passengerRepository.save(passenger);
    }

    public Passenger loginPassenger(String email, String password) {
        Passenger passenger = passengerRepository.findByEmail(email);
        if (passenger == null || !passwordEncoder.matches(password, passenger.getPassword())) {
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
        // You can update other fields here as needed
        return passengerRepository.save(passenger);
    }

    public void deletePassenger(int id) {
        passengerRepository.deleteById(id);
    }
    
    public String bookRide(CreatePassengerRideDTO createPassengerRideDTO) {
        // Call ridematchingservice to book a ride
        String url = RIDE_MATCHING_SERVICE_URL + "/book";
        return restTemplate.postForObject(url, createPassengerRideDTO, String.class);
    }
    
    public List<PublisherRideDTO> getPassengerRides(int passengerId) {
        String url = RIDE_MATCHING_SERVICE_URL + "/passenger/" + passengerId + "/rides";
        return restTemplate.getForObject(url, List.class);
    }
    
    @Override
    public List<PublisherRideDTO> getFilteredRides(String fromLocation, String toLocation) {
        String url = "http://localhost:8090/rides/filter?fromLocation=" + fromLocation + "&toLocation=" + toLocation;
        PublisherRideDTO[] rides = restTemplate.getForObject(url, PublisherRideDTO[].class);
        return Arrays.asList(rides);
    }
    
    //cancel booking
    @Override
    public void cancelBooking(int passengerRideId) {
        String url = RIDE_MATCHING_SERVICE_URL + "/cancel/" + passengerRideId;
        restTemplate.delete(url);
    }

}
