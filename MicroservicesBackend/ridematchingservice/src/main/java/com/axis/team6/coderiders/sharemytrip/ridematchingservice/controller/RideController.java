package com.axis.team6.coderiders.sharemytrip.ridematchingservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.BookRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.CreatePublisherRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.PublisherRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PassengerRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PublisherRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.service.RideService;

@RestController
@RequestMapping("/rides")
public class RideController {
    @Autowired
    private RideService rideService;

    @PostMapping("/publish")
    public ResponseEntity<PublisherRide> createPublisherRide(@RequestBody @Valid CreatePublisherRideDTO createPublisherRideDTO) {
        PublisherRide publisherRide = rideService.createPublisherRide(createPublisherRideDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherRide);
    }

    @PostMapping("/book")
    public ResponseEntity<PassengerRide> bookRide(@RequestBody @Valid BookRideDTO bookRideDTO) {
        PassengerRide passengerRide = rideService.bookRide(bookRideDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(passengerRide);
    }

    @GetMapping("/available")
    public ResponseEntity<List<PublisherRide>> viewAvailableRides() {
        List<PublisherRide> availableRides = rideService.viewAvailableRides();
        return ResponseEntity.ok(availableRides);
    }

    @DeleteMapping("/cancel/{passengerRideId}")
    public ResponseEntity<Void> cancelRide(@PathVariable Integer passengerRideId) {
        rideService.cancelRide(passengerRideId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cancel-published/{publisherRideId}")
    public ResponseEntity<Void> cancelPublishedRide(@PathVariable Integer publisherRideId) {
        rideService.cancelPublishedRide(publisherRideId);
        return ResponseEntity.noContent().build();
    }
    
    
    //View booked rides for a particular passeenger 
    @GetMapping("/passenger/{id}/rides")
    public ResponseEntity<List<PublisherRideDTO>> getPassengerRides(@PathVariable int id) {
        List<PublisherRideDTO> rides = rideService.getPassengerRides(id);
        return ResponseEntity.ok(rides);
    }
    
    @GetMapping("/filter")
    public ResponseEntity<List<PublisherRideDTO>> getFilteredRides(
            @RequestParam String fromLocation,
            @RequestParam String toLocation) {
        List<PublisherRideDTO> rides = rideService.getFilteredRides(fromLocation, toLocation);
        return ResponseEntity.ok(rides);
    }
}
