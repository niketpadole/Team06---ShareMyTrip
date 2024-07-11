package com.axis.usermanagementservice.controller;

import com.axis.usermanagementservice.dto.*;
import com.axis.usermanagementservice.entity.Passenger;
import com.axis.usermanagementservice.service.PassengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PassengerControllerTest {

    @InjectMocks
    private PassengerController passengerController;

    @Mock
    private PassengerService passengerService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void registerPassenger() {
//        PassengerRegistrationRequest registrationRequest = new PassengerRegistrationRequest();
//        when(passengerService.registerPassenger(any(PassengerRegistrationRequest.class))).thenReturn(new PassengerDTO());
//
//        ResponseEntity<?> response = passengerController.registerPassenger(registrationRequest);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(passengerService, times(1)).registerPassenger(any(PassengerRegistrationRequest.class));
//    }

//    @Test
//    void loginPassenger() {
//        LoginRequest loginRequest = new LoginRequest("test@test.com", "password");
//        PassengerDTO passengerDTO = new PassengerDTO();
//        when(passengerService.loginPassenger(anyString(), anyString())).thenReturn(passengerDTO);
//        when(restTemplate.postForEntity(anyString(), any(), eq(String.class))).thenReturn(new ResponseEntity<>("token", HttpStatus.OK));
//
//        ResponseEntity<PassengerDTO> response = passengerController.loginPassenger(loginRequest);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals("token", response.getBody().getToken());
//        verify(passengerService, times(1)).loginPassenger(anyString(), anyString());
//    }

    @Test
    void getAllPassengers() {
        List<PassengerDTO> passengers = Arrays.asList(new PassengerDTO());
        when(passengerService.getAllPassengers()).thenReturn(passengers);

        ResponseEntity<List<PassengerDTO>> response = passengerController.getAllPassengers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(passengerService, times(1)).getAllPassengers();
    }

    @Test
    void getPassengerById() {
        Passenger passenger = new Passenger();
        PassengerDTO passengerDTO = new PassengerDTO();
        when(passengerService.getPassengerById(anyInt())).thenReturn(passenger);
        when(modelMapper.map(any(Passenger.class), eq(PassengerDTO.class))).thenReturn(passengerDTO);

        ResponseEntity<PassengerDTO> response = passengerController.getPassengerById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(passengerService, times(1)).getPassengerById(1);
    }

    @Test
    void updatePassenger() {
        Passenger passenger = new Passenger();
        PassengerUpdateDTO passengerUpdateDTO = new PassengerUpdateDTO();
        when(modelMapper.map(any(PassengerUpdateDTO.class), eq(Passenger.class))).thenReturn(passenger);
        when(passengerService.updatePassenger(anyInt(), any(Passenger.class))).thenReturn(passenger);

        ResponseEntity<PassengerUpdateDTO> response = passengerController.updatePassenger(1, passengerUpdateDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(passengerService, times(1)).updatePassenger(anyInt(), any(Passenger.class));
    }

    @Test
    void deletePassenger() {
        doNothing().when(passengerService).deletePassenger(anyInt());

        ResponseEntity<Void> response = passengerController.deletePassenger(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(passengerService, times(1)).deletePassenger(1);
    }

    @Test
    void bookRide() {
        CreatePassengerRideDTO createPassengerRideDTO = new CreatePassengerRideDTO();
        when(passengerService.bookRide(any(CreatePassengerRideDTO.class))).thenReturn("Ride booked");

        ResponseEntity<String> response = passengerController.bookRide(1, createPassengerRideDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Ride booked", response.getBody());
        verify(passengerService, times(1)).bookRide(any(CreatePassengerRideDTO.class));
    }

    @Test
    void getPassengerRidesDetails() {
        List<RideDetailsDTO> rides = Arrays.asList(new RideDetailsDTO());
        when(restTemplate.getForObject(anyString(), eq(List.class))).thenReturn(rides);

        List<RideDetailsDTO> response = passengerController.getPassengerRidesDetails(1);

        assertNotNull(response);
        assertEquals(1, response.size());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(List.class));
    }

    @Test
    void getFilteredRides() {
        List<PublisherRideDTO> rides = Arrays.asList(new PublisherRideDTO());
        when(passengerService.getFilteredRides(anyString(), anyString(), anyString())).thenReturn(rides);

        ResponseEntity<List<PublisherRideDTO>> response = passengerController.getFilteredRides("from", "to", new Date());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(passengerService, times(1)).getFilteredRides(anyString(), anyString(), anyString());
    }

    @Test
    void cancelBooking() {
        doNothing().when(passengerService).cancelBooking(anyInt());

        ResponseEntity<Void> response = passengerController.cancelBooking(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(passengerService, times(1)).cancelBooking(1);
    }

    @Test
    void viewCompletedRides() {
        List<RideDetailsDTO> rides = Arrays.asList(new RideDetailsDTO());
        when(passengerService.viewRidesById(anyInt())).thenReturn(rides);

        ResponseEntity<List<RideDetailsDTO>> response = passengerController.viewCompletedRides(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(passengerService, times(1)).viewRidesById(1);
    }

    @Test
    void viewNotCompletedRides() {
        List<RideDetailsDTO> rides = Arrays.asList(new RideDetailsDTO());
        when(passengerService.viewNotCompletedRides(anyInt())).thenReturn(rides);

        ResponseEntity<List<RideDetailsDTO>> response = passengerController.viewNotCompletedRides(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(passengerService, times(1)).viewNotCompletedRides(1);
    }

    @Test
    void viewOngoingRidesPassenger() {
        List<RideDetailsDTO> rides = Arrays.asList(new RideDetailsDTO());
        when(passengerService.viewOngoingRidesPassenger(anyInt())).thenReturn(rides);

        ResponseEntity<List<RideDetailsDTO>> response = passengerController.viewOngoingRidesPassenger(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(passengerService, times(1)).viewOngoingRidesPassenger(1);
    }

    @Test
    void payRide() {
        TransactionLinkRequestDto requestDto = new TransactionLinkRequestDto();
        when(passengerService.payRide(any(TransactionLinkRequestDto.class))).thenReturn("Payment successful");

        ResponseEntity<String> response = passengerController.payRide(requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Payment successful", response.getBody());
        verify(passengerService, times(1)).payRide(any(TransactionLinkRequestDto.class));
    }
}
