package com.axis.team6.coderiders.sharemytrip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.team6.coderiders.sharemytrip.entity.Publisher;
import com.axis.team6.coderiders.sharemytrip.exception.PublisherNotFoundException;
import com.axis.team6.coderiders.sharemytrip.repository.PublisherRepository;
import com.axis.team6.coderiders.sharemytrip.utils.PublisherRegistrationRequest;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public Publisher registerPublisher(PublisherRegistrationRequest registrationRequest) {
        Publisher publisher = new Publisher();
        publisher.setFirstName(registrationRequest.getFirstName());
        publisher.setLastName(registrationRequest.getLastName());
        publisher.setMobile(registrationRequest.getMobile());
        publisher.setDateOfBirth(registrationRequest.getDateOfBirth());
        publisher.setEmail(registrationRequest.getEmail());
        publisher.setDrivingLicense(registrationRequest.getDrivingLicense());
        publisher.setAadharCard(registrationRequest.getAadharCard());
        publisher.setMiniBio(registrationRequest.getMiniBio());
        publisher.setPassword(registrationRequest.getPassword());
        publisher.setVehicleModelName(registrationRequest.getVehicleModelName());
        publisher.setVehicleNo(registrationRequest.getVehicleNo());
        // Set timestamp
        publisher.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
        return publisherRepository.save(publisher);
    }

    public Publisher loginPublisher(String email, String password) {
        Publisher publisher = publisherRepository.findByEmailAndPassword(email, password);
        if (publisher == null) {
            throw new PublisherNotFoundException("Invalid email or password");
        }
        return publisher;
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Publisher getPublisherById(int id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException("Publisher not found with id: " + id));
    }

    public Publisher updatePublisher(int id, Publisher publisherDetails) {
        Publisher publisher = getPublisherById(id);
        publisher.setFirstName(publisherDetails.getFirstName());
        publisher.setLastName(publisherDetails.getLastName());
        publisher.setMobile(publisherDetails.getMobile());
        publisher.setDateOfBirth(publisherDetails.getDateOfBirth());
        publisher.setEmail(publisherDetails.getEmail());
        publisher.setDrivingLicense(publisherDetails.getDrivingLicense());
        publisher.setAadharCard(publisherDetails.getAadharCard());
        publisher.setMiniBio(publisherDetails.getMiniBio());
        publisher.setPassword(publisherDetails.getPassword());
        publisher.setVehicleModelName(publisherDetails.getVehicleModelName());
        publisher.setVehicleNo(publisherDetails.getVehicleNo());
        // Set timestamp
        publisher.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
        return publisherRepository.save(publisher);
    }

    public void deletePublisher(int id) {
        if (!publisherRepository.existsById(id)) {
            throw new PublisherNotFoundException("Publisher not found with id: " + id);
        }
        publisherRepository.deleteById(id);
    }
}
