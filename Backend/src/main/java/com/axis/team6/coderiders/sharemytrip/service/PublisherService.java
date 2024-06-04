package com.axis.team6.coderiders.sharemytrip.service;

import java.util.List;

import com.axis.team6.coderiders.sharemytrip.entity.Publisher;
import com.axis.team6.coderiders.sharemytrip.utils.PublisherRegistrationRequest;

public interface PublisherService {
	public Publisher registerPublisher(PublisherRegistrationRequest registrationRequest);
    public Publisher loginPublisher(String email, String password);
    public List<Publisher> getAllPublishers();
    public Publisher getPublisherById(int id);
    public Publisher updatePublisher(int id, Publisher publisherDetails);
    public void deletePublisher(int id);
}
