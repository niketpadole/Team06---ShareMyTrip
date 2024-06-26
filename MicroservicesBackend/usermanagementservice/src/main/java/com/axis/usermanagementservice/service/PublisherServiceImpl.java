package com.axis.usermanagementservice.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.RestTemplate;

import com.axis.usermanagementservice.dto.AuthRequest;
import com.axis.usermanagementservice.dto.CreatePublisherRideDTO;
import com.axis.usermanagementservice.dto.PublisherDTO;
import com.axis.usermanagementservice.dto.PublisherRegistrationRequest;
import com.axis.usermanagementservice.dto.PublisherRideDTO;
import com.axis.usermanagementservice.dto.PublisherUpdateDTO;
import com.axis.usermanagementservice.dto.RideDetailsDTO;
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
	@LoadBalanced
	private RestTemplate restTemplate;

	private static final String AUTH_SERVER_URL="http://authserver/auth/register";
	private final String RIDE_MATCHING_URL="http://ridematchingservice/rides";

	public ResponseEntity<?> registerPublisher(PublisherRegistrationRequest registrationRequest) {
		Publisher publisher = modelMapper.map(registrationRequest, Publisher.class);
		publisher.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
		// Set timestamp
		publisher.setTimestamp(new Timestamp(System.currentTimeMillis()));
		if (publisherRepository.findByEmail(publisher.getEmail()) != null) {
			return new ResponseEntity<>("Email Already Registered..Please Login", HttpStatus.CONFLICT);
		} else {
			AuthRequest authRequest = new AuthRequest(registrationRequest.getEmail(),
					registrationRequest.getPassword());
			restTemplate.postForEntity(AUTH_SERVER_URL, authRequest, String.class);
			publisherRepository.save(publisher);
			return new ResponseEntity<>("Registered successfully..Please Login", HttpStatus.OK);
		}

	}

	public PublisherDTO loginPublisher(String email, String password) {
		Publisher publisher = publisherRepository.findByEmail(email);
		if (publisher == null || !passwordEncoder.matches(password, publisher.getPassword())) {
			return null;
		}
		PublisherDTO publisherDTO = modelMapper.map(publisher,PublisherDTO.class);
		return publisherDTO;
	}


	public List<PublisherDTO> getAllPublishers() {
		return publisherRepository.findAll().stream().map(p -> modelMapper.map(p, PublisherDTO.class))
				.collect(Collectors.toList());
	}

	public PublisherDTO getPublisherById(int id) {
			return publisherRepository.findById(id).stream().map(p->modelMapper.map(p, PublisherDTO.class)).findAny().orElseThrow(()-> new PublisherNotFoundException("No such publisher Id"));
				
	}

	public String updatePublisher(int id, PublisherUpdateDTO publisherDetails) {
		PublisherDTO publisher=getPublisherById(id);
		if (publisherDetails.getFirstName() != null) {
			publisher.setFirstName(publisherDetails.getFirstName());
		}
		if (publisherDetails.getLastName() != null) {
			publisher.setLastName(publisherDetails.getLastName());
		}
		if (publisherDetails.getDateOfBirth() != null) {
			publisher.setDateOfBirth(publisherDetails.getDateOfBirth());
		}
		if (publisherDetails.getAadharCard() != null) {
			publisher.setAadharCard(publisherDetails.getAadharCard());
		}
		if (publisherDetails.getMiniBio() != null) {
			publisher.setMiniBio(publisherDetails.getMiniBio());
		}
		if (publisherDetails.getVehicleModelName() != null) {
			publisher.setVehicleModelName(publisherDetails.getVehicleModelName());
		}
		if (publisherDetails.getVehicleNo() != null) {
			publisher.setVehicleNo(publisherDetails.getVehicleNo());
		}
		Publisher p=modelMapper.map(publisher, Publisher.class);
		// publisher.setPassword(passwordEncoder.encode(publisherDetails.getPassword()));
		publisherRepository.save(p);
		return "Updated Successfully";
	}

	public void deletePublisher(int id) {
		if (!publisherRepository.existsById(id)) {
			throw new PublisherNotFoundException("Publisher not found with id: " + id);
		}
		publisherRepository.deleteById(id);
	}

	public String createRide(CreatePublisherRideDTO createPublisherRideDTO) {
		String rideMatchingServiceUrl = RIDE_MATCHING_URL+"/publish";
		return restTemplate.postForObject(rideMatchingServiceUrl, createPublisherRideDTO, String.class);

	}

	@Override//at /rides/ "/publisher/{id}/completed"
	public List<PublisherRideDTO> viewRidesById(int id) {
		 String rideMatchingServiceUrl = RIDE_MATCHING_URL+"/publisher/" + id + "/completed";
		// Call the REST API using getForObject and convert the response to an array
        PublisherRideDTO[] response = restTemplate.getForObject(rideMatchingServiceUrl, PublisherRideDTO[].class);
        
        //getForObject does not support ParameterizedTypeReference.. Alternative -> use exchange
        
        // Convert the array to a List
        return Arrays.asList(response);
		
	}

	@Override
	public void setStatus(Integer id) {
		String rideMatchingServiceUrl = RIDE_MATCHING_URL+"/publisher/";//@PutMapping("/publisher/{id}/start")
		restTemplate.put(rideMatchingServiceUrl+id+"/start",null);
		
	}

	@Override
	public List<PublisherRideDTO> viewNotCompletedRides(Integer publisherId) {
		String rideMatchingServiceUrl = RIDE_MATCHING_URL+"/publisher/"+publisherId+"/not-completed";
		PublisherRideDTO[] response=restTemplate.getForObject(rideMatchingServiceUrl, PublisherRideDTO[].class);
		if(response==null)
			return null;
		return Arrays.asList(response);
		
	}

	@Override
	public List<PublisherRideDTO> viewOngoingRides(int id) {
		String rideMatchingServiceUrl = RIDE_MATCHING_URL+"/publisher/"+id+"/ongoing";
		PublisherRideDTO[] response=restTemplate.getForObject(rideMatchingServiceUrl, PublisherRideDTO[].class);
		return Arrays.asList(response);
	}

	@Override
	public List<RideDetailsDTO> viewPassengers(int publisherRideId) {
		String rideMatchingServiceUrl = RIDE_MATCHING_URL+"/publisher/"+publisherRideId+"/passengers";
		RideDetailsDTO[] response=restTemplate.getForObject(rideMatchingServiceUrl, RideDetailsDTO[].class);
		return Arrays.asList(response);
	}

	//fetch all rides by publisherId
	@Override
	public List<PublisherRideDTO> viewAllPublisherRides(int publisherId) {
	
		PublisherRideDTO[] response= restTemplate.getForObject(RIDE_MATCHING_URL+"/publisher/"+publisherId+"/rides", PublisherRideDTO[].class);
		return Arrays.asList(response);
	}

	@Override
	public void setStatusEnd(Integer id) {
		String rideMatchingServiceUrl = RIDE_MATCHING_URL+"/publisher/";//@PutMapping("/publisher/{id}/start")
		restTemplate.put(rideMatchingServiceUrl+id+"/end",null);
		
	}


}
