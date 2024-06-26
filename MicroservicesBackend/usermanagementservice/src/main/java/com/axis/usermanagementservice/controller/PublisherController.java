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
import org.springframework.web.bind.annotation.RestController;

import com.axis.usermanagementservice.dto.CreatePublisherRideDTO;
import com.axis.usermanagementservice.dto.LoginRequest;
import com.axis.usermanagementservice.dto.PublisherRegistrationRequest;
import com.axis.usermanagementservice.entity.Publisher;
import com.axis.usermanagementservice.service.PublisherService;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<Publisher> registerPublisher(@RequestBody PublisherRegistrationRequest registrationRequest) {
        Publisher publisher = modelMapper.map(registrationRequest, Publisher.class);
        Publisher registeredPublisher = publisherService.registerPublisher(registrationRequest);
        return ResponseEntity.ok(registeredPublisher);
    }

    @PostMapping("/login")
    public ResponseEntity<Publisher> loginPublisher(@RequestBody LoginRequest loginRequest) {
        Publisher publisher = publisherService.loginPublisher(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(publisher);
    }

    @GetMapping
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        List<Publisher> publishers = publisherService.getAllPublishers();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable int id) {
        Publisher publisher = publisherService.getPublisherById(id);
        return ResponseEntity.ok(publisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable int id, @RequestBody PublisherRegistrationRequest publisherRegistrationRequest) {
        Publisher publisherDetails = modelMapper.map(publisherRegistrationRequest, Publisher.class);
        Publisher updatedPublisher = publisherService.updatePublisher(id, publisherDetails);
        return ResponseEntity.ok(updatedPublisher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable int id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @PostMapping("/{id}/rides")
    public ResponseEntity<String> createRide(@PathVariable int id, @RequestBody CreatePublisherRideDTO createPublisherRideDTO) {
        createPublisherRideDTO.setPublisherId(id);
        String response = publisherService.createRide(createPublisherRideDTO);
        return ResponseEntity.ok(response);
    }
}
