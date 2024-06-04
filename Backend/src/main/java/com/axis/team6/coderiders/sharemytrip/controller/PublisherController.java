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

import com.axis.team6.coderiders.sharemytrip.entity.Publisher;
import com.axis.team6.coderiders.sharemytrip.service.PublisherService;
import com.axis.team6.coderiders.sharemytrip.utils.LoginRequest;
import com.axis.team6.coderiders.sharemytrip.utils.PublisherRegistrationRequest;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @PostMapping("/register")
    public Publisher registerPublisher(@RequestBody PublisherRegistrationRequest registrationRequest) {
        return publisherService.registerPublisher(registrationRequest);
    }

    @PostMapping("/login")
    public Publisher loginPublisher(@RequestBody LoginRequest loginRequest) {
        return publisherService.loginPublisher(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    public Publisher getPublisherById(@PathVariable int id) {
        return publisherService.getPublisherById(id);
    }

    @PutMapping("/{id}")
    public Publisher updatePublisher(@PathVariable int id, @RequestBody Publisher publisherDetails) {
        return publisherService.updatePublisher(id, publisherDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable int id) {
        publisherService.deletePublisher(id);
    }
}
