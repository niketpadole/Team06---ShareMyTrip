package com.axis.usermanagementservice.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.axis.usermanagementservice.dto.AuthRequest;
import com.axis.usermanagementservice.dto.CreatePassengerRideDTO;
import com.axis.usermanagementservice.dto.LoginRequest;
import com.axis.usermanagementservice.dto.PassengerDTO;
import com.axis.usermanagementservice.dto.PassengerRegistrationRequest;
import com.axis.usermanagementservice.dto.PassengerUpdateDTO;
import com.axis.usermanagementservice.dto.PublisherRideDTO;
import com.axis.usermanagementservice.dto.RideDetailsDTO;
import com.axis.usermanagementservice.dto.TransactionLinkRequestDto;
import com.axis.usermanagementservice.entity.Passenger;
import com.axis.usermanagementservice.service.PassengerService;

@RestController
@RequestMapping("/user/passengers")
@CrossOrigin("*")
public class PassengerController {

	@Autowired
	private PassengerService passengerService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	// private static final String AUTH_SERVICE_URL =
	// "http://authservice/auth/register";
	private static final String RIDE_MATCHING_SERVICE_URL = "http://ridematchingservice/rides/passenger";
	private static final String AUTH_SERVER_URL="http://authserver";

	@PostMapping("/register")
	public ResponseEntity<?> registerPassenger(@RequestBody PassengerRegistrationRequest registrationRequest) {
		return new ResponseEntity<>(passengerService.registerPassenger(registrationRequest), HttpStatus.OK);

	}

	@PostMapping("/login")
	public ResponseEntity<PassengerDTO> loginPassenger(@RequestBody LoginRequest loginRequest) {
		PassengerDTO passenger = passengerService.loginPassenger(loginRequest.getEmail(), loginRequest.getPassword());
		if (passenger != null) {
			AuthRequest authRequest = new AuthRequest(loginRequest.getEmail(), loginRequest.getPassword());
			ResponseEntity<String> authResponse = restTemplate.postForEntity(AUTH_SERVER_URL+"/auth/token", authRequest, String.class);
			if (authResponse.getStatusCode() == HttpStatus.OK && authResponse.getBody() != null) {
				String token = authResponse.getBody();
//				HttpHeaders headers = new HttpHeaders();
//				headers.set("Authorization", "Bearer " + token);
				passenger.setToken(token);
				return ResponseEntity.ok().body(passenger);
			} else {
				return ResponseEntity.status(authResponse.getStatusCode()).body(null);
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}

	@GetMapping
	public ResponseEntity<List<PassengerDTO>> getAllPassengers() {
		List<PassengerDTO> passengers = passengerService.getAllPassengers();
		return ResponseEntity.ok(passengers);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PassengerDTO> getPassengerById(@PathVariable int id) {
		Passenger response=passengerService.getPassengerById(id);
		return ResponseEntity.ok(modelMapper.map(response,PassengerDTO.class));
	}

	// update passenger details
	@PutMapping("/{id}")
	public ResponseEntity<PassengerUpdateDTO> updatePassenger(@PathVariable int id,
			@RequestBody PassengerUpdateDTO passengerUpdateDTO) {
		Passenger passengerDetails = modelMapper.map(passengerUpdateDTO, Passenger.class);
		System.out.println(passengerUpdateDTO);
		System.out.println(passengerDetails);
		Passenger updatedPassenger = passengerService.updatePassenger(id, passengerDetails);
		return ResponseEntity.ok(passengerUpdateDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePassenger(@PathVariable int id) {
		passengerService.deletePassenger(id);
		return ResponseEntity.noContent().build();
	}

	/************Passenger APIs*************/
	
	@PostMapping("/{id}/book")
	public ResponseEntity<String> bookRide(@PathVariable int id,
			@RequestBody CreatePassengerRideDTO createPassengerRideDTO) {
		createPassengerRideDTO.setPassengerId(id);
		String response = passengerService.bookRide(createPassengerRideDTO);
		return ResponseEntity.ok(response);
	}

//    @GetMapping("/{id}/rides")
//    public ResponseEntity<List<PublisherRideDTO>> getPassengerRides(@PathVariable int id) {
//        List<PublisherRideDTO> rides = passengerService.getPassengerRides(id);
//        return ResponseEntity.ok(rides);
//    }

	@GetMapping("/{passengerId}/rides")
	public List<RideDetailsDTO> getPassengerRidesDetails(@PathVariable int passengerId) {
		String url = RIDE_MATCHING_SERVICE_URL + "/" + passengerId + "/rides";
		return restTemplate.getForObject(url, List.class);
	}

//	@InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        dateFormat.setLenient(false);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//    }
	
	@GetMapping("/rides/filter")
	public ResponseEntity<List<PublisherRideDTO>> getFilteredRides(@RequestParam String fromLocation,
			@RequestParam String toLocation,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfJourney) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(dateOfJourney);
		List<PublisherRideDTO> rides = passengerService.getFilteredRides(fromLocation, toLocation,dateStr);
		return ResponseEntity.ok(rides);
	}

	// cancel ride
	@DeleteMapping("/cancel/{passengerRideId}")
	public ResponseEntity<Void> cancelBooking(@PathVariable int passengerRideId) {
		passengerService.cancelBooking(passengerRideId);
		return ResponseEntity.noContent().build();
	}
	
	
    
    //Find completed rides(past rides) for passenger by its Id
	@GetMapping("/{passengerId}/rides/completed")
    public ResponseEntity<List<RideDetailsDTO>> viewCompletedRides(@PathVariable Integer passengerId) {
    	List<RideDetailsDTO> p=passengerService.viewRidesById(passengerId);
    	if(p!=null)
    	return ResponseEntity.ok(p);
    	return ResponseEntity.noContent().build();
    }
	
	
    //view NOT_COMPLETED rides
    @GetMapping("/{id}/rides/not-completed")
    public ResponseEntity<List<RideDetailsDTO>> viewNotCompletedRides(@PathVariable int id) {
    	List<RideDetailsDTO> p=passengerService.viewNotCompletedRides(id);
    	if(p!=null)
    	return ResponseEntity.ok(p);
    	return ResponseEntity.noContent().build();
    } 
    
    //view ONGOING rides
    @GetMapping("/{id}/rides/ongoing")
    public ResponseEntity<List<RideDetailsDTO>> viewOngoingRidesPassenger(@PathVariable int id) {
    	List<RideDetailsDTO> p=passengerService.viewOngoingRidesPassenger(id);
    	if(p!=null)
    	return ResponseEntity.ok(p);
    	return ResponseEntity.noContent().build();
    }
	
    //Pay for a COMPLETED Ride
    @PostMapping("/pay")
    public ResponseEntity<String> payRide(@RequestBody TransactionLinkRequestDto rideDetails){
    	String res=passengerService.payRide(rideDetails);
    //	if(res==null)//Payment not done
    		//return new ResponseEntity<String>("Payment unsuccessful",HttpStatus.EXPECTATION_FAILED);
//    	else//payment successful
//    	{
//    		//update ride status of passenger to Completed and payment_status to PAID
//    		String response=passengerService.setRideStatus(rideDetails.getPassengerRideId());
//    		if(response=="success")
//    			return new ResponseEntity<String>("success",HttpStatus.ACCEPTED);
//    		return new ResponseEntity<String>("failed",HttpStatus.BAD_GATEWAY);
//    	}
    		
    	return ResponseEntity.ok(res);//
    }
	
	
}
