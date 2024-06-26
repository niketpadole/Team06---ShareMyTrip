package com.axis.usermanagementservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.axis.usermanagementservice.dto.CreatePublisherRideDTO;
import com.axis.usermanagementservice.dto.PublisherDTO;
import com.axis.usermanagementservice.dto.PublisherRegistrationRequest;
import com.axis.usermanagementservice.dto.PublisherRideDTO;
import com.axis.usermanagementservice.dto.PublisherUpdateDTO;
import com.axis.usermanagementservice.dto.RideDetailsDTO;
import com.axis.usermanagementservice.entity.Publisher;

@Service
public interface PublisherService {
    ResponseEntity<?> registerPublisher(PublisherRegistrationRequest registrationRequest);
    PublisherDTO loginPublisher(String email, String password);
    List<PublisherDTO> getAllPublishers();
    PublisherDTO getPublisherById(int id);
    String updatePublisher(int id, PublisherUpdateDTO publisherDetails);
    void deletePublisher(int id);
    
    public String createRide(CreatePublisherRideDTO createPublisherRideDTO);
	List<PublisherRideDTO> viewRidesById(int id);
	void setStatus(Integer id);
	List<PublisherRideDTO> viewNotCompletedRides(Integer id);
	List<PublisherRideDTO> viewOngoingRides(int id);
	List<RideDetailsDTO> viewPassengers(int publisherRideId);
	List<PublisherRideDTO> viewAllPublisherRides(int publisherId);
	void setStatusEnd(Integer id);
}
