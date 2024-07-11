package com.axis.usermanagementservice.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.axis.usermanagementservice.dto.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.RestTemplate;

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

	private static final String AUTH_SERVER_URL = "http://apigateway:8095/auth/register";
	private final String RIDE_MATCHING_URL = "http://apigateway:8095/rides";
	private static final String PAYMENT_SERVICE_URL = "http://apigateway:8095/api/transactions";

	// public ResponseEntity<?> registerPublisher(PublisherRegistrationRequest registrationRequest) {
	// 	Publisher publisher = modelMapper.map(registrationRequest, Publisher.class);
	// 	publisher.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
	// 	// Set timestamp
	// 	publisher.setTimestamp(new Timestamp(System.currentTimeMillis()));
	// 	if (publisherRepository.findByEmail(publisher.getEmail()) != null) {
	// 		return new ResponseEntity<>("Email Already Registered..Please Login", HttpStatus.CONFLICT);
	// 	}
	// 	if(publisherRepository.findByDrivingLicense(registrationRequest.getDrivingLicense()) != null) {
	// 		return new ResponseEntity<>("License Already Registered..Please Login", HttpStatus.CONFLICT);
	// 	}
	// 	if(publisherRepository.findByAadharCard(registrationRequest.getAadharCard()) != null) {
	// 		return new ResponseEntity<>("Aadhar Card Already Registered..Please Login", HttpStatus.CONFLICT);
	// 	}
	// 		else {
	// 		AuthRequest authRequest = new AuthRequest(registrationRequest.getEmail(),
	// 				registrationRequest.getPassword());
	// 		restTemplate.postForEntity(AUTH_SERVER_URL, authRequest, String.class);
	// 		publisherRepository.save(publisher);
	// 		return new ResponseEntity<>("Registered successfully..Please Login", HttpStatus.OK);
	// 	}

	// }
	public ResponseEntity<?> registerPublisher(PublisherRegistrationRequest registrationRequest) {
		Publisher publisher = modelMapper.map(registrationRequest, Publisher.class);
		publisher.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
		publisher.setTimestamp(new Timestamp(System.currentTimeMillis()));
		if (publisherRepository.findByEmail(publisher.getEmail()) != null) {
			return new ResponseEntity<>("Email Already Registered..Please Login", HttpStatus.CONFLICT);
		}
		if (publisherRepository.findByDrivingLicense(registrationRequest.getDrivingLicense()) != null) {
			return new ResponseEntity<>("License Already Registered..Please Login", HttpStatus.CONFLICT);
		}
		if (publisherRepository.findByAadharCard(registrationRequest.getAadharCard()) != null) {
			return new ResponseEntity<>("Aadhar Card Already Registered..Please Login", HttpStatus.CONFLICT);
		} else {
			AuthRequest authRequest = new AuthRequest(registrationRequest.getEmail(),
					registrationRequest.getPassword(), "publisher");
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
		PublisherDTO publisherDTO = modelMapper.map(publisher, PublisherDTO.class);
		return publisherDTO;
	}

	public List<PublisherDTO> getAllPublishers() {
		return publisherRepository.findAll().stream().map(p -> modelMapper.map(p, PublisherDTO.class))
				.collect(Collectors.toList());
	}

	public PublisherDTO getPublisherById(int id) {
		return publisherRepository.findById(id).stream().map(p -> modelMapper.map(p, PublisherDTO.class)).findAny()
				.orElseThrow(() -> new PublisherNotFoundException("No such publisher Id"));

	}

	public String updatePublisher(int id, PublisherUpdateDTO publisherDetails) {
		PublisherDTO publisher = getPublisherById(id);
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
		Publisher p = modelMapper.map(publisher, Publisher.class);
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
		String rideMatchingServiceUrl = RIDE_MATCHING_URL + "/publish";
		return restTemplate.postForObject(rideMatchingServiceUrl, createPublisherRideDTO, String.class);

	}

	@Override // at /rides/ "/publisher/{id}/completed"
	public List<PublisherRideDTO> viewRidesById(int id) {
		String rideMatchingServiceUrl = RIDE_MATCHING_URL + "/publisher/" + id + "/completed";
		// Call the REST API using getForObject and convert the response to an array
		PublisherRideDTO[] response = restTemplate.getForObject(rideMatchingServiceUrl, PublisherRideDTO[].class);

		// getForObject does not support ParameterizedTypeReference.. Alternative -> use
		// exchange

		// Convert the array to a List
		return Arrays.asList(response);

	}

	@Override
	public void setStatus(Integer id) {
		String rideMatchingServiceUrl = RIDE_MATCHING_URL + "/publisher/";// @PutMapping("/publisher/{id}/start")
		restTemplate.put(rideMatchingServiceUrl + id + "/start", null);

	}

	@Override
	public List<PublisherRideDTO> viewNotCompletedRides(Integer publisherId) {
		String rideMatchingServiceUrl = RIDE_MATCHING_URL + "/publisher/" + publisherId + "/not-completed";
		PublisherRideDTO[] response = restTemplate.getForObject(rideMatchingServiceUrl, PublisherRideDTO[].class);
		if (response == null)
			return null;
		return Arrays.asList(response);

	}

	@Override
	public List<PublisherRideDTO> viewOngoingRides(int id) {
		String rideMatchingServiceUrl = RIDE_MATCHING_URL + "/publisher/" + id + "/ongoing";
		PublisherRideDTO[] response = restTemplate.getForObject(rideMatchingServiceUrl, PublisherRideDTO[].class);
		return Arrays.asList(response);
	}

	@Override
	public List<RideDetailsDTO> viewPassengers(int publisherRideId) {
		String rideMatchingServiceUrl = RIDE_MATCHING_URL + "/publisher/" + publisherRideId + "/passengers";
		RideDetailsDTO[] response = restTemplate.getForObject(rideMatchingServiceUrl, RideDetailsDTO[].class);
		return Arrays.asList(response);
	}

	// fetch all rides by publisherId
	@Override
	public List<PublisherRideDTO> viewAllPublisherRides(int publisherId) {

		PublisherRideDTO[] response = restTemplate
				.getForObject(RIDE_MATCHING_URL + "/publisher/" + publisherId + "/rides", PublisherRideDTO[].class);
		return Arrays.asList(response);
	}

	@Override
	public void setStatusEnd(Integer id) {
		String rideMatchingServiceUrl = RIDE_MATCHING_URL + "/publisher/";// @PutMapping("/publisher/{id}/start")
		restTemplate.put(rideMatchingServiceUrl + id + "/end", null);

	}

	@Override
	public Publisher getPublisherByEmail(String email) {
		return publisherRepository.findByEmail(email);
	}
	@Override
	public void updatePassword(String email, String newPassword, String userType) {
		Publisher publisher = publisherRepository.findByEmail(email);
		if (publisher == null) {
			throw new PublisherNotFoundException("Publisher Not Found");
		}
		String encodedPassword = passwordEncoder.encode(newPassword);
		publisher.setPassword(encodedPassword);
		publisherRepository.save(publisher);

		AuthRequest authRequest = new AuthRequest(email, newPassword, userType);
		String response = restTemplate.postForObject("http://apigateway:8095/auth/update", authRequest, String.class);
		System.out.println(response);
	}

	// @Override
	// public void updatePassword(String email, String newPassword) {
	// 	Publisher publisher = publisherRepository.findByEmail(email);
	// 	if (publisher == null) {
	// 		throw new PublisherNotFoundException("Publisher Not Found");
	// 	}
	// 	String encodedPassword = passwordEncoder.encode(newPassword);
	// 	publisher.setPassword(encodedPassword);
	// 	publisherRepository.save(publisher);
	// 	AuthRequest authRequest = new AuthRequest();
	// 	authRequest.setEmail(email);
	// 	authRequest.setPassword(newPassword);
	// 	String response = restTemplate.postForObject("http://authserver:9898/auth/update", authRequest, String.class);
	// 	System.out.println(response);
	// 	;
	// }
//
//	@Override
//	public void allPassengersPaid(RideDetailsDTO rideDetailsDTO) {
////	restTemplate.put(RIDE_MATCHING_URL+"/passenger/"+rideDetailsDTO.getPassengerRideId()+"/paid",String.class);
//	HttpEntity<RideDetailsDTO> requestEntity = new HttpEntity<>(rideDetailsDTO);
//
//		// Make the PUT request and get the response entity
//		ResponseEntity<String> response = restTemplate.exchange(RIDE_MATCHING_URL+"/passenger/"+rideDetailsDTO.getPassengerRideId()+"/paid", HttpMethod.PUT, requestEntity, String.class);
////		if(response.equals("ALL_PAID")) {
//		if ("ALL_PAID".equals(response.getBody())) {
//
//			Publisher p = publisherRepository.findById(rideDetailsDTO.getPublisherId()).orElse(null);
//				p.setTotalEarnings(p.getTotalEarnings() + (rideDetailsDTO.getPassengerCount() * rideDetailsDTO.getFare()));
//			}
//			publisherRepository.save(p);
//		}
//	}

	@Transactional
	@Override
	public void updateTotalEarnings(RideDetailsDTO rideDetailsDTO) {
		HttpEntity<RideDetailsDTO> requestEntity = new HttpEntity<>(rideDetailsDTO);
		ResponseEntity<String> response = restTemplate.exchange(
				RIDE_MATCHING_URL + "/passenger/" + rideDetailsDTO.getPassengerRideId() + "/paid", HttpMethod.PUT,
				requestEntity, String.class);

		if ("ALL_PAID".equals(response.getBody())) {
			Publisher publisher = publisherRepository.findById(rideDetailsDTO.getPublisherId()).orElse(null);
			if (publisher != null) {
				float totalEarnings = publisher.getTotalEarnings() + (rideDetailsDTO.getFare());
				publisher.setTotalEarnings(totalEarnings);
				publisherRepository.save(publisher);
			}
		}
		if ("PARTIAL_PAID".equals(response.getBody())) {
			Publisher publisher = publisherRepository.findById(rideDetailsDTO.getPublisherId()).orElse(null);
			if (publisher != null) {
				float totalEarnings = publisher.getTotalEarnings() + (rideDetailsDTO.getFare());
				publisher.setTotalEarnings(totalEarnings);
				publisherRepository.save(publisher);
			}

		}
	}

	@Override
	public List<TransactionDetailsDTO> getTransactions(Integer publisherRideId) {
		TransactionDetailsDTO[] res = restTemplate.getForObject(
				PAYMENT_SERVICE_URL + "/" + publisherRideId + "/transactions", TransactionDetailsDTO[].class);
		return Arrays.asList(res);
	}
}
