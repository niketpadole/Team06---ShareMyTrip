package com.axis.usermanagementservice.service;

import com.axis.usermanagementservice.dto.*;
import com.axis.usermanagementservice.entity.Passenger;
import com.axis.usermanagementservice.exception.PassengerNotFoundException;
import com.axis.usermanagementservice.repository.PassengerRepository;
import com.axis.usermanagementservice.repository.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PassengerServiceImplTest {

    @InjectMocks
    private PassengerServiceImpl passengerService;

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private PublisherRepository publisherRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerPassenger() {
        PassengerRegistrationRequest registrationRequest = new PassengerRegistrationRequest();
        registrationRequest.setEmail("test@test.com");
        registrationRequest.setPassword("password");

        Passenger passenger = new Passenger();
        passenger.setEmail("test@test.com");
        passenger.setPassword("password");

        when(passengerRepository.findByEmail(anyString())).thenReturn(null);
        when(modelMapper.map(any(PassengerRegistrationRequest.class), eq(Passenger.class))).thenReturn(passenger);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(restTemplate.postForEntity(anyString(), any(), eq(Void.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<?> response = passengerService.registerPassenger(registrationRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(passengerRepository, times(1)).save(any(Passenger.class));
    }

    @Test
    void loginPassenger() {
        String email = "test@test.com";
        String password = "password";
        Passenger passenger = new Passenger();
        passenger.setEmail(email);
        passenger.setPassword("encodedPassword");

        when(passengerRepository.findByEmail(anyString())).thenReturn(passenger);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(modelMapper.map(any(Passenger.class), eq(PassengerDTO.class))).thenReturn(new PassengerDTO());

        PassengerDTO result = passengerService.loginPassenger(email, password);

        assertNotNull(result);
        verify(passengerRepository, times(1)).findByEmail(email);
    }

    @Test
    void getAllPassengers() {
        List<Passenger> passengers = Arrays.asList(new Passenger());
        when(passengerRepository.findAll()).thenReturn(passengers);
        when(modelMapper.map(any(Passenger.class), eq(PassengerDTO.class))).thenReturn(new PassengerDTO());

        List<PassengerDTO> result = passengerService.getAllPassengers();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(passengerRepository, times(1)).findAll();
    }

    @Test
    void getPassengerById() {
        Passenger passenger = new Passenger();
        when(passengerRepository.findById(anyInt())).thenReturn(Optional.of(passenger));

        Passenger result = passengerService.getPassengerById(1);

        assertNotNull(result);
        verify(passengerRepository, times(1)).findById(1);
    }

    @Test
    void updatePassenger() {
        Passenger passenger = new Passenger();
        passenger.setFirstName("John");
        Passenger updatedPassenger = new Passenger();
        updatedPassenger.setFirstName("John Updated");

        when(passengerRepository.findById(anyInt())).thenReturn(Optional.of(passenger));
        when(passengerRepository.save(any(Passenger.class))).thenReturn(updatedPassenger);

        Passenger result = passengerService.updatePassenger(1, updatedPassenger);

        assertNotNull(result);
        assertEquals("John Updated", result.getFirstName());
        verify(passengerRepository, times(1)).findById(1);
        verify(passengerRepository, times(1)).save(any(Passenger.class));
    }

    @Test
    void deletePassenger() {
        doNothing().when(passengerRepository).deleteById(anyInt());

        passengerService.deletePassenger(1);

        verify(passengerRepository, times(1)).deleteById(1);
    }

    @Test
    void bookRide() {
        CreatePassengerRideDTO createPassengerRideDTO = new CreatePassengerRideDTO();
        when(restTemplate.postForObject(anyString(), any(), eq(String.class))).thenReturn("Ride booked");

        String result = passengerService.bookRide(createPassengerRideDTO);

        assertEquals("Ride booked", result);
        verify(restTemplate, times(1)).postForObject(anyString(), any(), eq(String.class));
    }

    @Test
    void getPassengerRides() {
        List<PublisherRideDTO> rides = Arrays.asList(new PublisherRideDTO());
        when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(rides);

        List<PublisherRideDTO> result = passengerService.getPassengerRides(1);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(List.class));
    }

    @Test
    void getFilteredRides() {
        PublisherRideDTO[] rides = new PublisherRideDTO[]{new PublisherRideDTO()};
        when(restTemplate.getForObject(anyString(), eq(PublisherRideDTO[].class))).thenReturn(rides);

        List<PublisherRideDTO> result = passengerService.getFilteredRides("from", "to", "2023-12-25");

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(PublisherRideDTO[].class));
    }

    @Test
    void cancelBooking() {
        doNothing().when(restTemplate).delete(anyString());

        passengerService.cancelBooking(1);

        verify(restTemplate, times(1)).delete(anyString());
    }

    @Test
    void getPassengerByEmail() {
        Passenger passenger = new Passenger();
        when(passengerRepository.findByEmail(anyString())).thenReturn(passenger);
        when(modelMapper.map(any(Passenger.class), eq(PassengerDTO.class))).thenReturn(new PassengerDTO());

        PassengerDTO result = passengerService.getPassengerByEmail("test@test.com");

        assertNotNull(result);
        verify(passengerRepository, times(1)).findByEmail("test@test.com");
    }

    @Test
    void viewRidesById() {
        RideDetailsDTO[] rides = new RideDetailsDTO[]{new RideDetailsDTO()};
        when(restTemplate.getForObject(anyString(), eq(RideDetailsDTO[].class))).thenReturn(rides);

        List<RideDetailsDTO> result = passengerService.viewRidesById(1);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(RideDetailsDTO[].class));
    }

    @Test
    void viewNotCompletedRides() {
        RideDetailsDTO[] rides = new RideDetailsDTO[]{new RideDetailsDTO()};
        when(restTemplate.getForObject(anyString(), eq(RideDetailsDTO[].class))).thenReturn(rides);

        List<RideDetailsDTO> result = passengerService.viewNotCompletedRides(1);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(RideDetailsDTO[].class));
    }

    @Test
    void viewOngoingRidesPassenger() {
        RideDetailsDTO[] rides = new RideDetailsDTO[]{new RideDetailsDTO()};
        when(restTemplate.getForObject(anyString(), eq(RideDetailsDTO[].class))).thenReturn(rides);

        List<RideDetailsDTO> result = passengerService.viewOngoingRidesPassenger(1);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(RideDetailsDTO[].class));
    }

    @Test
    void payRide() {
        TransactionLinkRequestDto requestDto = new TransactionLinkRequestDto();
        when(restTemplate.postForObject(anyString(), any(), eq(String.class))).thenReturn("Payment successful");

        String result = passengerService.payRide(requestDto);

        assertEquals("Payment successful", result);
        verify(restTemplate, times(1)).postForObject(anyString(), any(), eq(String.class));
    }

    @Test
    void setRideStatus() {
        HttpEntity<Void> requestEntity = new HttpEntity<>(null);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Ride status updated", HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), eq(requestEntity), eq(String.class))).thenReturn(responseEntity);

        String result = passengerService.setRideStatus(1);

        assertEquals("Ride status updated", result);
        verify(restTemplate, times(1)).exchange(anyString(), eq(HttpMethod.PUT), eq(requestEntity), eq(String.class));
    }
}
