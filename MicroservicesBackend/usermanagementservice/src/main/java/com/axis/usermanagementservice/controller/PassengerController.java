package com.axis.usermanagementservice.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axis.usermanagementservice.dto.CreatePassengerRideDTO;
import com.axis.usermanagementservice.dto.LoginRequest;
import com.axis.usermanagementservice.dto.PassengerRegistrationRequest;
import com.axis.usermanagementservice.dto.PublisherRideDTO;
import com.axis.usermanagementservice.entity.Passenger;
import com.axis.usermanagementservice.service.PassengerService;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<Passenger> registerPassenger(@RequestBody PassengerRegistrationRequest registrationRequest) {
        Passenger passenger = passengerService.registerPassenger(registrationRequest);
        return ResponseEntity.ok(passenger);
    }

    @PostMapping("/login")
    public ResponseEntity<Passenger> loginPassenger(@RequestBody LoginRequest loginRequest) {
        Passenger passenger = passengerService.loginPassenger(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(passenger);
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        List<Passenger> passengers = passengerService.getAllPassengers();
        return ResponseEntity.ok(passengers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable int id) {
        Passenger passenger = passengerService.getPassengerById(id);
        return ResponseEntity.ok(passenger);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable int id, @RequestBody PassengerRegistrationRequest passengerRegistrationRequest) {
        Passenger passengerDetails = modelMapper.map(passengerRegistrationRequest, Passenger.class);
        Passenger updatedPassenger = passengerService.updatePassenger(id, passengerDetails);
        return ResponseEntity.ok(updatedPassenger);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable int id) {
        passengerService.deletePassenger(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{id}/book")
    public ResponseEntity<String> bookRide(@PathVariable int id, @RequestBody CreatePassengerRideDTO createPassengerRideDTO) {
        createPassengerRideDTO.setPassengerId(id);
        String response = passengerService.bookRide(createPassengerRideDTO);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}/rides")
    public ResponseEntity<List<PublisherRideDTO>> getPassengerRides(@PathVariable int id) {
        List<PublisherRideDTO> rides = passengerService.getPassengerRides(id);
        return ResponseEntity.ok(rides);
    }
    
    @GetMapping("/rides/filter")
    public ResponseEntity<List<PublisherRideDTO>> getFilteredRides(
            @RequestParam String fromLocation,
            @RequestParam String toLocation) {
        List<PublisherRideDTO> rides = passengerService.getFilteredRides(fromLocation, toLocation);
        return ResponseEntity.ok(rides);
    }
}
