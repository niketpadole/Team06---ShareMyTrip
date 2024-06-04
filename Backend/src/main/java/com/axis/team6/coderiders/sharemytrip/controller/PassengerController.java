package com.axis.team6.coderiders.sharemytrip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axis.team6.coderiders.sharemytrip.entity.Passenger;
import com.axis.team6.coderiders.sharemytrip.service.PassengerService;
import com.axis.team6.coderiders.sharemytrip.utils.LoginRequest;
import com.axis.team6.coderiders.sharemytrip.utils.PassengerRegistrationRequest;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping("/register")
    public Passenger registerPassenger(@RequestBody PassengerRegistrationRequest registrationRequest) {
        return passengerService.registerPassenger(registrationRequest);
    }

    @PostMapping("/login")
    public Passenger loginPassenger(@RequestBody LoginRequest loginRequest) {
        return passengerService.loginPassenger(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @GetMapping
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/{id}")
    public Passenger getPassengerById(@PathVariable int id) {
        return passengerService.getPassengerById(id);
    }

    @PutMapping("/{id}")
    public Passenger updatePassenger(@PathVariable int id, @RequestBody Passenger passengerDetails) {
        return passengerService.updatePassenger(id, passengerDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable int id) {
        passengerService.deletePassenger(id);
    }
}
