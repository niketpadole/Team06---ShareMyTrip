package com.axis.usermanagementservice.service;

import com.axis.usermanagementservice.dto.*;
import com.axis.usermanagementservice.entity.Passenger;
import com.axis.usermanagementservice.repository.PassengerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PassengerServiceTest {

    @InjectMocks
    private PassengerServiceImpl passengerService;

    @Mock
    private PassengerRepository passengerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void registerPassenger() {
//        PassengerRegistrationRequest request = new PassengerRegistrationRequest();
//        when(passengerRepository.save(any(Passenger.class))).thenReturn(new Passenger());
//
//        ResponseEntity<?> response = passengerService.registerPassenger(request);
//        assertNotNull(response);
//        verify(passengerRepository, times(1)).save(any(Passenger.class));
//    }

//    @Test
//    void loginPassenger() {
//        Passenger passenger = new Passenger();
//        passenger.setEmail("test@example.com");
//        passenger.setPassword("password");
//        when(passengerRepository.findByEmail("test@example.com")).thenReturn(passenger);
//
//        PassengerDTO result = passengerService.loginPassenger("test@example.com", "password");
//        assertNotNull(result);
//        assertEquals("test@example.com", result.getEmail());
//        verify(passengerRepository, times(1)).findByEmail("test@example.com");
//    }

//    @Test
//    void getAllPassengers() {
//        List<Passenger> passengers = Arrays.asList(new Passenger(), new Passenger());
//        when(passengerRepository.findAll()).thenReturn(passengers);
//
//        List<PassengerDTO> result = passengerService.getAllPassengers();
//        assertEquals(2, result.size());
//        verify(passengerRepository, times(1)).findAll();
//    }

    @Test
    void getPassengerById() {
        Passenger passenger = new Passenger();
        when(passengerRepository.findById(1)).thenReturn(Optional.of(passenger));

        Passenger result = passengerService.getPassengerById(1);
        assertNotNull(result);
        verify(passengerRepository, times(1)).findById(1);
    }

//    @Test
//    void updatePassenger() {
//        Passenger passenger = new Passenger();
//        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);
//
//        Passenger result = passengerService.updatePassenger(1, passenger);
//        assertNotNull(result);
//        verify(passengerRepository, times(1)).save(any(Passenger.class));
//    }

    @Test
    void deletePassenger() {
        doNothing().when(passengerRepository).deleteById(1);

        passengerService.deletePassenger(1);
        verify(passengerRepository, times(1)).deleteById(1);
    }

//    @Test
//    void bookRide() {
//        CreatePassengerRideDTO rideDTO = new CreatePassengerRideDTO();
//        String bookingReference = "BOOK123";
//        when(passengerRepository.bo(rideDTO)).thenReturn(bookingReference);
//
//        String result = passengerService.bookRide(rideDTO);
//        assertEquals(bookingReference, result);
//        verify(passengerRepository, times(1)).bookRide(rideDTO);
//    }

//    @Test
//    void getPassengerRides() {
//        List<PublisherRideDTO> rides = Arrays.asList(new PublisherRideDTO(), new PublisherRideDTO());
//        when(passengerRepository.(1)).thenReturn(rides);
//
//        List<PublisherRideDTO> result = passengerService.getPassengerRides(1);
//        assertEquals(2, result.size());
//        verify(passengerRepository, times(1)).findRidesByPassengerId(1);
//    }

//    @Test
//    void getFilteredRides() {
//        List<PublisherRideDTO> rides = Arrays.asList(new PublisherRideDTO(), new PublisherRideDTO());
//        when(passengerRepository.findFilteredRides("LocationA", "LocationB", "2024-06-22")).thenReturn(rides);
//
//        List<PublisherRideDTO> result = passengerService.getFilteredRides("LocationA", "LocationB", "2024-06-22");
//        assertEquals(2, result.size());
//        verify(passengerRepository, times(1)).findFilteredRides("LocationA", "LocationB", "2024-06-22");
//    }

//    @Test
//    void cancelBooking() {
//        doNothing().when(passengerRepository).cancelBooking(1);
//
//        passengerService.cancelBooking(1);
//        verify(passengerRepository, times(1)).cancelBooking(1);
//    }

//    @Test
//    void getPassengerByEmail() {
//        Passenger passenger = new Passenger();
//        when(passengerRepository.findByEmail("test@example.com")).thenReturn(passenger);
//
//        PassengerDTO result = passengerService.getPassengerByEmail("test@example.com");
//        assertNotNull(result);
//        assertEquals("test@example.com", result.getEmail());
//        verify(passengerRepository, times(1)).findByEmail("test@example.com");
//    }

//    @Test
//    void viewRidesById() {
//        List<RideDetailsDTO> rides = Arrays.asList(new RideDetailsDTO(), new RideDetailsDTO());
//        when(passengerRepository.viewRidesById(1)).thenReturn(rides);
//
//        List<RideDetailsDTO> result = passengerService.viewRidesById(1);
//        assertEquals(2, result.size());
//        verify(passengerRepository, times(1)).viewRidesById(1);
//    }

//    @Test
//    void viewNotCompletedRides() {
//        List<RideDetailsDTO> rides = Arrays.asList(new RideDetailsDTO(), new RideDetailsDTO());
//        when(passengerRepository.viewNotCompletedRides(1)).thenReturn(rides);
//
//        List<RideDetailsDTO> result = passengerService.viewNotCompletedRides(1);
//        assertEquals(2, result.size());
//        verify(passengerRepository, times(1)).viewNotCompletedRides(1);
//    }

//    @Test
//    void viewOngoingRidesPassenger() {
//        List<RideDetailsDTO> rides = Arrays.asList(new RideDetailsDTO(), new RideDetailsDTO());
//        when(passengerRepository.viewOngoingRidesPassenger(1)).thenReturn(rides);
//
//        List<RideDetailsDTO> result = passengerService.viewOngoingRidesPassenger(1);
//        assertEquals(2, result.size());
//        verify(passengerRepository, times(1)).viewOngoingRidesPassenger(1);
//    }

//    @Test
//    void payRide() {
//        TransactionLinkRequestDto requestDto = new TransactionLinkRequestDto();
//        String paymentConfirmation = "PAY123";
//        when(passengerRepository.payRide(requestDto)).thenReturn(paymentConfirmation);
//
//        String result = passengerService.payRide(requestDto);
//        assertEquals(paymentConfirmation, result);
//        verify(passengerRepository, times(1)).payRide(requestDto);
//    }

//    @Test
//    void setRideStatus() {
//        String status = "COMPLETED";
//        when(passengerRepository.setRideStatus(1)).thenReturn(status);
//
//        String result = passengerService.setRideStatus(1);
//        assertEquals(status, result);
//        verify(passengerRepository, times(1)).setRideStatus(1);
//    }
}
