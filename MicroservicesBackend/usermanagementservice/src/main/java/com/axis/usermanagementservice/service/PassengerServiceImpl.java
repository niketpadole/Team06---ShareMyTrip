package com.axis.usermanagementservice.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.axis.usermanagementservice.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.axis.usermanagementservice.entity.Passenger;
import com.axis.usermanagementservice.exception.PassengerNotFoundException;
import com.axis.usermanagementservice.repository.PassengerRepository;
import com.axis.usermanagementservice.repository.PublisherRepository;

@Service
public class PassengerServiceImpl implements PassengerService {

	private static final String AUTH_SERVICE_URL = "http://apigateway:8095/auth/register";
	private static final String RIDE_MATCHING_SERVICE_URL = "http://apigateway:8095/rides";
	private static final String PAYMENT_SERVICE_URL="http://apigateway:8095/api/transactions";

	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	@Autowired
	private ModelMapper modelMapper;

	// public ResponseEntity<?> registerPassenger(PassengerRegistrationRequest registrationRequest) {
	// 	Passenger passenger = modelMapper.map(registrationRequest, Passenger.class);
	// 	passenger.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
	// 	passenger.setTimestamp(new Timestamp(System.currentTimeMillis()));
	// 	// Register passenger credentials in the auth service
	// 	if (passengerRepository.findByEmail(passenger.getEmail()) != null) {
	// 		return new ResponseEntity<>("Email is already registered..Please Login!!!", HttpStatus.CONFLICT);
	// 	}
	// 	if (passengerRepository.findByAadharCard(passenger.getAadharCard()) != null) {
	// 		return new ResponseEntity<>("Aadhar already registered..Please Login!!!", HttpStatus.CONFLICT);
	// 	}
	// 	else {
	// 		AuthRequest authRequest = new AuthRequest();
	// 		authRequest.setEmail(registrationRequest.getEmail());
	// 		authRequest.setPassword(registrationRequest.getPassword());
	// 		ResponseEntity<Void> response = restTemplate.postForEntity(AUTH_SERVICE_URL, authRequest, Void.class);

	// 		if (response.getStatusCode().is2xxSuccessful()) {
	// 			passengerRepository.save(passenger);
	// 			return new ResponseEntity<>("Registration successful", HttpStatus.CREATED);
	// 		} else {
	// 			// Handle the case when registration in auth service fails
	// 			return new ResponseEntity<>("Authentication server error", HttpStatus.BAD_GATEWAY);
	// 		}
	// 	}
	// }
	public ResponseEntity<?> registerPassenger(PassengerRegistrationRequest registrationRequest) {
		Passenger passenger = modelMapper.map(registrationRequest, Passenger.class);
		passenger.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
		passenger.setTimestamp(new Timestamp(System.currentTimeMillis()));
		if (passengerRepository.findByEmail(passenger.getEmail()) != null) {
			return new ResponseEntity<>("Email is already registered..Please Login!!!", HttpStatus.CONFLICT);
		}
		if (passengerRepository.findByAadharCard(passenger.getAadharCard()) != null) {
			return new ResponseEntity<>("Aadhar already registered..Please Login!!!", HttpStatus.CONFLICT);
		} else {
			AuthRequest authRequest = new AuthRequest(registrationRequest.getEmail(), registrationRequest.getPassword(), "passenger");
			ResponseEntity<Void> response = restTemplate.postForEntity(AUTH_SERVICE_URL, authRequest, Void.class);

			if (response.getStatusCode().is2xxSuccessful()) {
				passengerRepository.save(passenger);
				return new ResponseEntity<>("Registration successful", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Authentication server error", HttpStatus.BAD_GATEWAY);
			}
		}
	}

	public PassengerDTO loginPassenger(String email, String password) {
		Passenger passenger = passengerRepository.findByEmail(email);
		if (passenger == null || !passwordEncoder.matches(password, passenger.getPassword())) {
			return null;
		}
		PassengerDTO passangerDto = modelMapper.map(passenger,PassengerDTO.class);
		return passangerDto;
	}


	public List<PassengerDTO> getAllPassengers() {
		return passengerRepository.findAll().stream().map(p -> modelMapper.map(p, PassengerDTO.class))
				.collect(Collectors.toList());
	}

	public Passenger getPassengerById(int id) {
		return passengerRepository.findById(id)
				.orElseThrow(() -> new PassengerNotFoundException("Passenger not found with id: " + id));
	}

	public Passenger updatePassenger(int id, Passenger passengerDetails) {
		Passenger passenger = getPassengerById(id);
		if (passengerDetails.getFirstName() != null) {
			passenger.setFirstName(passengerDetails.getFirstName());
		}
		if (passengerDetails.getLastName() != null) {
			passenger.setLastName(passengerDetails.getLastName());
		}
		if (passengerDetails.getMobile() != null) {
			passenger.setMobile(Long.parseLong(passengerDetails.getMobile().toString()));
		}
		if (passengerDetails.getDateOfBirth() != null) {
			passenger.setDateOfBirth(passengerDetails.getDateOfBirth());
		}
		if (passengerDetails.getAadharCard() != null) {
			passenger.setAadharCard(passengerDetails.getAadharCard());
		}
		if (passengerDetails.getMiniBio() != null) {
			passenger.setMiniBio(passengerDetails.getMiniBio());
		}
		System.out.println(passengerDetails);
		System.out.println(passenger);

		return passengerRepository.save(passenger);
	}

	public void deletePassenger(int id) {
		passengerRepository.deleteById(id);
	}

	public String bookRide(CreatePassengerRideDTO createPassengerRideDTO) {
		// Call ridematchingservice to book a ride
		String url = RIDE_MATCHING_SERVICE_URL + "/book";
		return restTemplate.postForObject(url, createPassengerRideDTO, String.class);
	}

	public List<PublisherRideDTO> getPassengerRides(int passengerId) {
		String url = RIDE_MATCHING_SERVICE_URL + "/passenger/" + passengerId + "/rides";
		return restTemplate.getForObject(url, List.class);
	}

	@Override
	public List<PublisherRideDTO> getFilteredRides(String fromLocation, String toLocation,String dateOfJourney) {
		String url = RIDE_MATCHING_SERVICE_URL+"/filter?fromLocation=" + fromLocation + "&toLocation=" + toLocation+"&dateOfJourney="+dateOfJourney;
		PublisherRideDTO[] rides = restTemplate.getForObject(url, PublisherRideDTO[].class);
		return Arrays.asList(rides);
	}

	// cancel booking
	@Override
	public void cancelBooking(int passengerRideId) {
		String url = RIDE_MATCHING_SERVICE_URL + "/cancel/" + passengerRideId;
		restTemplate.delete(url);
	}

	@Override
	public PassengerDTO getPassengerByEmail(String email) {
		Passenger p=passengerRepository.findByEmail(email);
		return modelMapper.map(p, PassengerDTO.class);
	}

	@Override
	public List<RideDetailsDTO> viewRidesById(Integer passengerId) {
		String url=RIDE_MATCHING_SERVICE_URL+"/passenger/"+passengerId+"/past-rides";
		RideDetailsDTO[] rides=restTemplate.getForObject(url, RideDetailsDTO[].class);
		return Arrays.asList(rides);
	}

	@Override
	public List<RideDetailsDTO> viewNotCompletedRides(int passengerId) {
		String url=RIDE_MATCHING_SERVICE_URL+"/passenger/"+passengerId+"/not-completed";
		RideDetailsDTO[] rides=restTemplate.getForObject(url, RideDetailsDTO[].class);
		return Arrays.asList(rides);
	}

	@Override
	public List<RideDetailsDTO> viewOngoingRidesPassenger(int passengerId) {
		String url=RIDE_MATCHING_SERVICE_URL+"/passenger/"+passengerId+"/ongoing";
		RideDetailsDTO[] rides=restTemplate.getForObject(url, RideDetailsDTO[].class);
		return Arrays.asList(rides);
	}

	@Override
	public String payRide(TransactionLinkRequestDto rideDetails) {
		rideDetails.setOrderId(rideDetails.getPassengerMobile()+ LocalDateTime.now());
			String res=restTemplate.postForObject(PAYMENT_SERVICE_URL+"?orderId="+rideDetails.getOrderId(), rideDetails, String.class);
			return res;


	}

	@Override
	public String setRideStatus(Integer passengerRideId) {
		//restTemplate.put(RIDE_MATCHING_SERVICE_URL+"/passenger/"+passengerRideId+"/paid", null); //returns void so use restTemplate.exchange()


		 // Create an HttpEntity with no body (null)
        HttpEntity<Void> requestEntity = new HttpEntity<>(null);

        // Use exchange method to make a PUT request and get the response as String
        ResponseEntity<String> response = restTemplate.exchange(RIDE_MATCHING_SERVICE_URL+"/passenger/"+passengerRideId+"/paid", HttpMethod.PUT, requestEntity, String.class);

        return response.getBody();

	}
	// @Override
	// public void updatePassword(String email, String newPassword) {
	// 	Passenger passenger = passengerRepository.findByEmail(email);
	// 	if(passenger != null)
	// 	{
	// 		String encodedPassword = passwordEncoder.encode(newPassword);
	// 		passenger.setPassword(encodedPassword);
	// 		passengerRepository.save(passenger);
	// 		AuthRequest authRequest = new AuthRequest();
	// 		authRequest.setEmail(email);
	// 		authRequest.setPassword(newPassword);
	// 		String response = restTemplate.postForObject("http://authserver:9898/auth/update",authRequest,String.class);
	// 	}
	// 	else{
	// 		throw  new PassengerNotFoundException("User Not Found");
	// 	}
	// }
	@Override
	public void updatePassword(String email, String newPassword, String userType) {
		Passenger passenger = passengerRepository.findByEmail(email);
		if (passenger != null) {
			String encodedPassword = passwordEncoder.encode(newPassword);
			passenger.setPassword(encodedPassword);
			passengerRepository.save(passenger);

			AuthRequest authRequest = new AuthRequest(email, newPassword, userType);
			String response = restTemplate.postForObject("http://authserver:9898/auth/update", authRequest, String.class);
			System.out.println(response);
		} else {
			throw new PassengerNotFoundException("User Not Found");
		}
	}

	@Override
	public List<TransactionDetailsDTO> getTransactions(Integer passengerId) {
		TransactionDetailsDTO[] res=restTemplate.getForObject(PAYMENT_SERVICE_URL+"/"+passengerId,TransactionDetailsDTO[].class);
		return Arrays.asList(res);
	}

//	@Override
//	public void updatePaymentStatus(UpdatePaymentStatusDTO updatePaymentStatusDTO) {
//		Passenger passenger = passengerRepository.findById(updatePaymentStatusDTO.getPassengerId())
//				.orElseThrow(() -> new PassengerNotFoundException("Passenger not found with id: " + updatePaymentStatusDTO.getPassengerId()));
//		passenger.setPaymentStatus(updatePaymentStatusDTO.getPaymentStatus());
//		passengerRepository.save(passenger);
//	}


}
