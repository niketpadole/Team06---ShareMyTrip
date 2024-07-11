package com.axis.usermanagementservice.service;

import com.axis.usermanagementservice.dto.*;
import com.axis.usermanagementservice.entity.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PublisherServiceTest {

    @Mock
    private PublisherService publisherService;

    @InjectMocks
    private PublisherServiceTest publisherServiceTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerPublisher() {
        PublisherRegistrationRequest registrationRequest = new PublisherRegistrationRequest();
        when(publisherService.registerPublisher(any(PublisherRegistrationRequest.class))).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> response = publisherService.registerPublisher(registrationRequest);
        assertEquals(ResponseEntity.ok().build(), response);
        verify(publisherService, times(1)).registerPublisher(any(PublisherRegistrationRequest.class));
    }

    @Test
    void loginPublisher() {
        String email = "test@example.com";
        String password = "password";
        PublisherDTO publisherDTO = new PublisherDTO();
        when(publisherService.loginPublisher(email, password)).thenReturn(publisherDTO);

        PublisherDTO result = publisherService.loginPublisher(email, password);
        assertNotNull(result);
        verify(publisherService, times(1)).loginPublisher(email, password);
    }

    @Test
    void getAllPublishers() {
        List<PublisherDTO> publishers = Collections.singletonList(new PublisherDTO());
        when(publisherService.getAllPublishers()).thenReturn(publishers);

        List<PublisherDTO> result = publisherService.getAllPublishers();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(publisherService, times(1)).getAllPublishers();
    }

    @Test
    void getPublisherById() {
        int id = 1;
        PublisherDTO publisherDTO = new PublisherDTO();
        when(publisherService.getPublisherById(id)).thenReturn(publisherDTO);

        PublisherDTO result = publisherService.getPublisherById(id);
        assertNotNull(result);
        verify(publisherService, times(1)).getPublisherById(id);
    }

    @Test
    void updatePublisher() {
        int id = 1;
        PublisherUpdateDTO publisherUpdateDTO = new PublisherUpdateDTO();
        when(publisherService.updatePublisher(id, publisherUpdateDTO)).thenReturn("Updated");

        String result = publisherService.updatePublisher(id, publisherUpdateDTO);
        assertEquals("Updated", result);
        verify(publisherService, times(1)).updatePublisher(id, publisherUpdateDTO);
    }

    @Test
    void deletePublisher() {
        int id = 1;
        doNothing().when(publisherService).deletePublisher(id);

        publisherService.deletePublisher(id);
        verify(publisherService, times(1)).deletePublisher(id);
    }

//    @Test
//    void createRide() {
//        CreatePublisherRideDTO createPublisherRideDTO = new CreatePublisherRideDTO();
//        when(publisherService.createRide(createPublisherRideDTO)).thenReturn(ResponseEntity.ok().build());
//
//        ResponseEntity<?> response = publisherService.createRide(createPublisherRideDTO);
//        assertEquals(ResponseEntity.ok().build(), response);
//        verify(publisherService, times(1)).createRide(createPublisherRideDTO);
//    }

//    @Test
//    void viewRidesById() {
//        int id = 1;
//        List<RideDetailsDTO> rideDetails = Collections.singletonList(new RideDetailsDTO());
//        when(publisherService.viewRidesById(id)).thenReturn(rideDetails);
//
//        List<RideDetailsDTO> result = publisherService.viewRidesById(id);
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        verify(publisherService, times(1)).viewRidesById(id);
//    }

//    @Test
//    void setStatus() {
//        int id = 1;
//        String status = "Completed";
//        when(publisherService.setStatus(id, status)).thenReturn("Status Updated");
//
//        String result = publisherService.setStatus(id, status);
//        assertEquals("Status Updated", result);
//        verify(publisherService, times(1)).setStatus(id, status);
//    }

//    @Test
//    void viewNotCompletedRides() {
//        int id = 1;
//        List<RideDetailsDTO> rideDetails = Collections.singletonList(new RideDetailsDTO());
//        when(publisherService.viewNotCompletedRides(id)).thenReturn(rideDetails);
//
//        List<RideDetailsDTO> result = publisherService.viewNotCompletedRides(id);
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        verify(publisherService, times(1)).viewNotCompletedRides(id);
//    }

//    @Test
//    void viewOngoingRides() {
//        int id = 1;
//        List<RideDetailsDTO> rideDetails = Collections.singletonList(new RideDetailsDTO());
//        when(publisherService.viewOngoingRides(id)).thenReturn(rideDetails);
//
//        List<RideDetailsDTO> result = publisherService.viewOngoingRides(id);
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        verify(publisherService, times(1)).viewOngoingRides(id);
//    }

//    @Test
//    void viewPassengers() {
//        int rideId = 1;
//        List<PassengerDTO> passengers = Collections.singletonList(new PassengerDTO());
//        when(publisherService.viewPassengers(rideId)).thenReturn(passengers);
//
//        List<PassengerDTO> result = publisherService.viewPassengers(rideId);
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        verify(publisherService, times(1)).viewPassengers(rideId);
//    }

//    @Test
//    void viewAllPublisherRides() {
//        int publisherId = 1;
//        List<PublisherRideDTO> rides = Collections.singletonList(new PublisherRideDTO());
//        when(publisherService.viewAllPublisherRides(publisherId)).thenReturn(rides);
//
//        List<PublisherRideDTO> result = publisherService.viewAllPublisherRides(publisherId);
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        verify(publisherService, times(1)).viewAllPublisherRides(publisherId);
//    }

//    @Test
//    void setStatusEnd() {
//        int rideId = 1;
//        String status = "Ended";
//        when(publisherService.setStatusEnd(rideId, status)).thenReturn("Status Ended");
//
//        String result = publisherService.setStatusEnd(rideId, status);
//        assertEquals("Status Ended", result);
//        verify(publisherService, times(1)).setStatusEnd(rideId, status);
//    }
}
