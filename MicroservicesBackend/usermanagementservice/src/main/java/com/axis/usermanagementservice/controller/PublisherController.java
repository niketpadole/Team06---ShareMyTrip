package com.axis.usermanagementservice.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.axis.usermanagementservice.dto.AuthRequest;
import com.axis.usermanagementservice.dto.CreatePublisherRideDTO;
import com.axis.usermanagementservice.dto.LoginRequest;
import com.axis.usermanagementservice.dto.PublisherDTO;
import com.axis.usermanagementservice.dto.PublisherRegistrationRequest;
import com.axis.usermanagementservice.dto.PublisherRideDTO;
import com.axis.usermanagementservice.dto.PublisherUpdateDTO;
import com.axis.usermanagementservice.dto.RideDetailsDTO;
import com.axis.usermanagementservice.entity.Publisher;
import com.axis.usermanagementservice.service.PublisherService;

@RestController
@RequestMapping("/user/publishers")
@CrossOrigin("*")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    private static final String AUTH_SERVER_URL="http://authserver/auth/token";
    @PostMapping("/register")
    public ResponseEntity<?> registerPublisher(@RequestBody PublisherRegistrationRequest registrationRequest) {
        //Publisher publisher = modelMapper.map(registrationRequest, Publisher.class);
        return publisherService.registerPublisher(registrationRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<PublisherDTO> loginPublisher(@RequestBody LoginRequest loginRequest) {
        PublisherDTO publisher = publisherService.loginPublisher(loginRequest.getEmail(), loginRequest.getPassword());
        if (publisher != null) {
            AuthRequest authRequest = new AuthRequest(loginRequest.getEmail(), loginRequest.getPassword());
            ResponseEntity<String> authResponse = restTemplate.postForEntity(AUTH_SERVER_URL, authRequest, String.class);
            if (authResponse.getStatusCode() == HttpStatus.OK && authResponse.getBody() != null) {
                String token = authResponse.getBody();
//				HttpHeaders headers = new HttpHeaders();
//				headers.set("Authorization", "Bearer " + token);
                publisher.setToken(token);
                return ResponseEntity.ok().body(publisher);
            } else {
                return ResponseEntity.status(authResponse.getStatusCode()).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


    @GetMapping
    public ResponseEntity<List<PublisherDTO>> getAllPublishers() {
        List<PublisherDTO> publishers = publisherService.getAllPublishers();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable int id) {
        PublisherDTO publisher = publisherService.getPublisherById(id);
        return ResponseEntity.ok(publisher);
    }

    
    //update publisher details
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePublisher(@PathVariable int id, @RequestBody PublisherUpdateDTO publisherUpdateDTO) {
//        Publisher publisherDetails = modelMapper.map(publisherUpdateDTO, Publisher.class);
        String updatedPublisher = publisherService.updatePublisher(id, publisherUpdateDTO);
        return ResponseEntity.ok(updatedPublisher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable int id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
    
    /*************Publisher APIs*********/
    @PostMapping("/{id}/rides")
    public ResponseEntity<String> createRide(@PathVariable int id, @RequestBody CreatePublisherRideDTO createPublisherRideDTO) {
        createPublisherRideDTO.setPublisherId(id);
        String response = publisherService.createRide(createPublisherRideDTO);
        return ResponseEntity.ok(response);
    }
    
    //View completed rides for publisher By Id
    //**********check for errors on response at postman********
    @GetMapping("/{id}/rides/completed")
    public ResponseEntity<List<PublisherRideDTO>> viewCompletedRides(@PathVariable int id) {
    	List<PublisherRideDTO> p=publisherService.viewRidesById(id);
    	if(p!=null)
    	return ResponseEntity.ok(p);
    	return ResponseEntity.noContent().build();
    }
    
    //Set ride status to ONGOING for publisher
    @PutMapping("/{id}/start")//id=publisher_ride_id
    public void setStatus(@PathVariable Integer id){
    	publisherService.setStatus(id);
    }
    
  //Set ride status to ONGOING for publisher
    @PutMapping("/{id}/end")//id=publisher_ride_id
    public void setStatusEnd(@PathVariable Integer id){
    	publisherService.setStatusEnd(id);
    }
    
    //view NOT_COMPLETED rides
    @GetMapping("/{publisherId}/rides/not-completed")
    public ResponseEntity<List<PublisherRideDTO>> viewNotCompletedRides(@PathVariable Integer publisherId) {
    	List<PublisherRideDTO> p=publisherService.viewNotCompletedRides(publisherId);
    	if(p!=null)
    	return ResponseEntity.ok(p);
    	return ResponseEntity.noContent().build();
    } 
    
    //view ONGOING rides
    @GetMapping("/{id}/rides/ongoing")
    public ResponseEntity<List<PublisherRideDTO>> viewOngoingRides(@PathVariable int id) {
    	List<PublisherRideDTO> p=publisherService.viewOngoingRides(id);
    	if(p!=null)
    	return ResponseEntity.ok(p);
    	return ResponseEntity.noContent().build();
    }

    //view passengers for a publisher_ride_id
    @GetMapping("/{publisherRideId}/passengers")
    public ResponseEntity<List<RideDetailsDTO>> viewPassengers(@PathVariable int publisherRideId) {
    	List<RideDetailsDTO> response=publisherService.viewPassengers(publisherRideId);
    	if(response!=null)
    	return ResponseEntity.ok(response);
    	return ResponseEntity.noContent().build();
    }
    
    //view All rides for a publisher
    @GetMapping("/{publisherId}/rides")
    public ResponseEntity<List<PublisherRideDTO>> viewAllRides(@PathVariable int publisherId) {
    	List<PublisherRideDTO> response=publisherService.viewAllPublisherRides(publisherId);
    	if(response!=null)
    	return ResponseEntity.ok(response);
    	return ResponseEntity.noContent().build();
    }
    
}
