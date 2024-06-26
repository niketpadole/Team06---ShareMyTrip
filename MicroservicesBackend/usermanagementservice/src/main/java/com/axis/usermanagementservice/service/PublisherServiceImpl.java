package com.axis.usermanagementservice.service;

import java.sql.Timestamp;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.axis.usermanagementservice.dto.CreatePublisherRideDTO;
import com.axis.usermanagementservice.dto.PublisherRegistrationRequest;
import com.axis.usermanagementservice.entity.Publisher;
import com.axis.usermanagementservice.exception.PublisherNotFoundException;
import com.axis.usermanagementservice.repository.PublisherRepository;

@Service
public class PublisherServiceImpl implements PublisherService {

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate;

	public Publisher registerPublisher(PublisherRegistrationRequest registrationRequest) {
		Publisher publisher = modelMapper.map(registrationRequest, Publisher.class);
		publisher.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
		// Set timestamp
		publisher.setTimestamp(new Timestamp(System.currentTimeMillis()));
		return publisherRepository.save(publisher);
	}

	public Publisher loginPublisher(String email, String password) {
		Publisher publisher = publisherRepository.findByEmail(email);
		if (publisher == null || !passwordEncoder.matches(password, publisher.getPassword())) {
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
		modelMapper.map(publisherDetails, publisher);
		publisher.setPassword(passwordEncoder.encode(publisherDetails.getPassword()));
		return publisherRepository.save(publisher);
	}

	public void deletePublisher(int id) {
		if (!publisherRepository.existsById(id)) {
			throw new PublisherNotFoundException("Publisher not found with id: " + id);
		}
		publisherRepository.deleteById(id);
	}

	 public String createRide(CreatePublisherRideDTO createPublisherRideDTO) {
	        String rideMatchingServiceUrl = "http://localhost:8090/rides/publish";
	        return restTemplate.postForObject(rideMatchingServiceUrl, createPublisherRideDTO, String.class);
	
	    }
	
	 //reject request
	 

}
