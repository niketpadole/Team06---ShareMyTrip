package com.axis.usermanagementservice.service;

import com.axis.usermanagementservice.dto.CreatePublisherRideDTO;
import com.axis.usermanagementservice.dto.PublisherRegistrationRequest;
import com.axis.usermanagementservice.entity.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PublisherService {
    Publisher registerPublisher(PublisherRegistrationRequest registrationRequest);
    Publisher loginPublisher(String email, String password);
    List<Publisher> getAllPublishers();
    Publisher getPublisherById(int id);
    Publisher updatePublisher(int id, Publisher publisherDetails);
    void deletePublisher(int id);
    
    public String createRide(CreatePublisherRideDTO createPublisherRideDTO);
}
