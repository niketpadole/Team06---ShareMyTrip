package com.axis.team6.coderiders.sharemytrip.ridematchingservice.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.BookRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.CreatePublisherRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.PublisherRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.RideDetailsDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PassengerRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PublisherRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.service.RideService;

@RestController
@RequestMapping("/rides")
@CrossOrigin("*")
public class RideController {
    @Autowired
    private RideService rideService;
    
    //All COMPLETED rides(ADMIN)
    @GetMapping("/completed")
    public ResponseEntity<List<PublisherRideDTO>> getAllCompletedRides(){
    	List<PublisherRideDTO> rideDetails=rideService.getCompletedRidesDetails();
    	return ResponseEntity.ok(rideDetails);
    }

    
    /*****************Publisher APIS***************/
    @PostMapping("/publish")
    public ResponseEntity<PublisherRide> createPublisherRide(@RequestBody @Valid CreatePublisherRideDTO createPublisherRideDTO) {
        PublisherRide publisherRide = rideService.createPublisherRide(createPublisherRideDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherRide);
    }

    // publisher can 
    @DeleteMapping("/cancel-published/{publisherRideId}")
    public ResponseEntity<Void> cancelPublishedRide(@PathVariable Integer publisherRideId) {
        rideService.cancelPublishedRide(publisherRideId);
        return ResponseEntity.noContent().build();
    }
    
    //All rides by publisherId
    @GetMapping("/publisher/{id}/rides")
    public ResponseEntity<List<PublisherRideDTO>> getPublisherRidesDetails(@PathVariable int id) {
        List<PublisherRideDTO> rideDetails=rideService.getPublisherRidesDetails(id);
        return ResponseEntity.ok(rideDetails);
    }
    
    //publisher COMPLETED rides by Id
    @GetMapping("/publisher/{id}/completed")
    public ResponseEntity<List<PublisherRideDTO>> getCompletedRides(@PathVariable @Valid Integer id){
    	List<PublisherRideDTO> rideDetails=rideService.getCompletedRides(id);
    	return ResponseEntity.ok(rideDetails);
    }
    
    //publisher change ride_status to "ONGOING"
    @PutMapping("/publisher/{id}/start")
    public ResponseEntity<String> setRideStatus(@PathVariable Integer id){
    	if(rideService.setRideStatus(id).equals("success"))
    	return ResponseEntity.ok("success");
    	return ResponseEntity.badRequest().body("Invalid");
    }
    
  //publisher change ride_status to "COMPLETED"
    @PutMapping("/publisher/{id}/end")
    public ResponseEntity<String> setRideStatusEnd(@PathVariable Integer id){
    	if(rideService.setRideStatusEnd(id).equals("success"))
    	return ResponseEntity.ok("success");
    	return ResponseEntity.badRequest().body("Invalid");
    }
    
    @GetMapping("/publisher/{publisherId}/not-completed")
    public ResponseEntity<List<PublisherRideDTO>> getNotCompletedRides(@PathVariable @Valid Integer publisherId){
    	List<PublisherRideDTO> rideDetails=rideService.getNotCompletedRides(publisherId);
    	//rideDetails.stream().forEach(System.out::println);
    	if(rideDetails==null)
    		return null;
    	return ResponseEntity.ok(rideDetails);
    }
    
    @GetMapping("/publisher/{id}/ongoing")
    public ResponseEntity<List<PublisherRideDTO>> getOngoingRides(@PathVariable @Valid Integer id){
    	List<PublisherRideDTO> rideDetails=rideService.getOngoingRides(id);
    	return ResponseEntity.ok(rideDetails);
    }
    
    @GetMapping("/publisher/{publisherRideId}/passengers")
    public ResponseEntity<List<RideDetailsDTO>> viewPassengers(@PathVariable @Valid Integer publisherRideId){
    	List<RideDetailsDTO> rideDetails=rideService.viewPassengers(publisherRideId);
    	return ResponseEntity.ok(rideDetails);
    }
    
    
    
    /**********************Passenger APIS************************/
    
    
    @PostMapping("/book")
    public ResponseEntity<PassengerRide> bookRide(@RequestBody @Valid BookRideDTO bookRideDTO) {
        PassengerRide passengerRide = rideService.bookRide(bookRideDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(passengerRide);
    }

    //All Available Rides(NOT_COMPLETED)
    @GetMapping("/available")
    public ResponseEntity<List<PublisherRideDTO>> viewAvailableRides() {
        List<PublisherRideDTO> availableRides = rideService.viewAvailableRides();
        return ResponseEntity.ok(availableRides);
    }

    
    // passenger can 
    @DeleteMapping("/cancel/{passengerRideId}")
    public ResponseEntity<Void> cancelRide(@PathVariable Integer passengerRideId) {
        rideService.cancelRide(passengerRideId);
        return ResponseEntity.noContent().build();
    }
    
  //Any user can
    @GetMapping("/filter")
    public ResponseEntity<List<PublisherRideDTO>> getFilteredRides(
            @RequestParam String fromLocation,
            @RequestParam String toLocation,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfJourney) {
        List<PublisherRideDTO> rides = rideService.getFilteredRides(fromLocation, toLocation,dateOfJourney);
        return ResponseEntity.ok(rides);
    }
    
    
    //View booked rides for a particular passeenger 
    @GetMapping("/passenger/{passengerId}/rides")
    public ResponseEntity<List<RideDetailsDTO>> getPassengerRidesDetails(@PathVariable int passengerId) {
        List<RideDetailsDTO> rideDetails = rideService.getPassengerRidesDetails(passengerId);
        return ResponseEntity.ok(rideDetails);
    }
    
    
    //COMPLETED rides
    @GetMapping("/passenger/{passengerId}/past-rides")
    public ResponseEntity<List<RideDetailsDTO>> viewPastRides(@PathVariable @Valid Integer passengerId){
    	List<RideDetailsDTO> rideDetails=rideService.viewPastRides(passengerId);
    	return ResponseEntity.ok(rideDetails);
    }
    
    
    //NOT_COMPLETED rides
    @GetMapping("/passenger/{passengerId}/not-completed")
    public ResponseEntity<List<RideDetailsDTO>> getNotCompletedRidesPassenger(@PathVariable @Valid Integer passengerId){
    	List<RideDetailsDTO> rideDetails=rideService.getNotCompletedRidesPassenger(passengerId);
    	rideDetails.stream().forEach(System.out::println);
    	if(rideDetails==null)
    		return null;
    	return ResponseEntity.ok(rideDetails);
    }
    
    //ONGOING rides
    @GetMapping("/passenger/{passengerId}/ongoing")
    public ResponseEntity<List<RideDetailsDTO>> getOngoingRidesPassenger(@PathVariable @Valid Integer passengerId){
    	List<RideDetailsDTO> rideDetails=rideService.getOngoingRidesPassenger(passengerId);
    	//rideDetails.stream().forEach(System.out::println);
    	if(rideDetails==null)
    		return null;
    	return ResponseEntity.ok(rideDetails);
    }

    @PutMapping("/passenger/{passengerRideId}/paid")
    public ResponseEntity<String> setRideStatusPassenger(@PathVariable Integer passengerRideId){
    	if(rideService.setRideStatusPassenger(passengerRideId).equals("success"))
    	return ResponseEntity.ok("success");
    	return ResponseEntity.badRequest().body("Invalid");
    }
}
