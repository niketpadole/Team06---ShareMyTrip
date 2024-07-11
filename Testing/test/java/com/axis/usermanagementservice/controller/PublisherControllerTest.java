package com.axis.usermanagementservice.controller;

import com.axis.usermanagementservice.dto.*;
import com.axis.usermanagementservice.service.PublisherService;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublisherControllerTest {

    @InjectMocks
    private PublisherController publisherController;

    @Mock
    private PublisherService publisherService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerPublisher() {
        PublisherRegistrationRequest registrationRequest = new PublisherRegistrationRequest();
        when(publisherService.registerPublisher(any(PublisherRegistrationRequest.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<?> response = publisherController.registerPublisher(registrationRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(publisherService, times(1)).registerPublisher(any(PublisherRegistrationRequest.class));
    }

    @Test
    void loginPublisher() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@test.com");
        loginRequest.setPassword("password");

        PublisherDTO publisherDTO = new PublisherDTO();
        when(publisherService.loginPublisher(anyString(), anyString())).thenReturn(publisherDTO);
        when(restTemplate.postForEntity(anyString(), any(), eq(String.class)))
                .thenReturn(new ResponseEntity<>("token", HttpStatus.OK));

        ResponseEntity<PublisherDTO> response = publisherController.loginPublisher(loginRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("token", response.getBody().getToken());
        verify(publisherService, times(1)).loginPublisher(anyString(), anyString());
    }

    @Test
    void getAllPublishers() {
        List<PublisherDTO> publishers = Arrays.asList(new PublisherDTO());
        when(publisherService.getAllPublishers()).thenReturn(publishers);

        ResponseEntity<List<PublisherDTO>> response = publisherController.getAllPublishers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(publisherService, times(1)).getAllPublishers();
    }

    @Test
    void getPublisherById() {
        PublisherDTO publisherDTO = new PublisherDTO();
        when(publisherService.getPublisherById(anyInt())).thenReturn(publisherDTO);

        ResponseEntity<PublisherDTO> response = publisherController.getPublisherById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(publisherService, times(1)).getPublisherById(1);
    }

    @Test
    void updatePublisher() {
        PublisherUpdateDTO publisherUpdateDTO = new PublisherUpdateDTO();
        when(publisherService.updatePublisher(anyInt(), any(PublisherUpdateDTO.class)))
                .thenReturn("Publisher updated");

        ResponseEntity<String> response = publisherController.updatePublisher(1, publisherUpdateDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Publisher updated", response.getBody());
        verify(publisherService, times(1)).updatePublisher(anyInt(), any(PublisherUpdateDTO.class));
    }

    @Test
    void deletePublisher() {
        doNothing().when(publisherService).deletePublisher(anyInt());

        ResponseEntity<Void> response = publisherController.deletePublisher(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(publisherService, times(1)).deletePublisher(1);
    }

    @Test
    void createRide() {
        CreatePublisherRideDTO createPublisherRideDTO = new CreatePublisherRideDTO();
        when(publisherService.createRide(any(CreatePublisherRideDTO.class)))
                .thenReturn("Ride created");

        ResponseEntity<String> response = publisherController.createRide(1, createPublisherRideDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Ride created", response.getBody());
        verify(publisherService, times(1)).createRide(any(CreatePublisherRideDTO.class));
    }

    @Test
    void viewCompletedRides() {
        List<PublisherRideDTO> rides = Arrays.asList(new PublisherRideDTO());
        when(publisherService.viewRidesById(anyInt())).thenReturn(rides);

        ResponseEntity<List<PublisherRideDTO>> response = publisherController.viewCompletedRides(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(publisherService, times(1)).viewRidesById(1);
    }

    @Test
    void setStatus() {
        doNothing().when(publisherService).setStatus(anyInt());

        publisherController.setStatus(1);

        verify(publisherService, times(1)).setStatus(1);
    }

    @Test
    void setStatusEnd() {
        doNothing().when(publisherService).setStatusEnd(anyInt());

        publisherController.setStatusEnd(1);

        verify(publisherService, times(1)).setStatusEnd(1);
    }

    @Test
    void viewNotCompletedRides() {
        List<PublisherRideDTO> rides = Arrays.asList(new PublisherRideDTO());
        when(publisherService.viewNotCompletedRides(anyInt())).thenReturn(rides);

        ResponseEntity<List<PublisherRideDTO>> response = publisherController.viewNotCompletedRides(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(publisherService, times(1)).viewNotCompletedRides(1);
    }

    @Test
    void viewOngoingRides() {
        List<PublisherRideDTO> rides = Arrays.asList(new PublisherRideDTO());
        when(publisherService.viewOngoingRides(anyInt())).thenReturn(rides);

        ResponseEntity<List<PublisherRideDTO>> response = publisherController.viewOngoingRides(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(publisherService, times(1)).viewOngoingRides(1);
    }

    @Test
    void viewPassengers() {
        List<RideDetailsDTO> passengers = Arrays.asList(new RideDetailsDTO());
        when(publisherService.viewPassengers(anyInt())).thenReturn(passengers);

        ResponseEntity<List<RideDetailsDTO>> response = publisherController.viewPassengers(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(publisherService, times(1)).viewPassengers(1);
    }

    @Test
    void viewAllRides() {
        List<PublisherRideDTO> rides = Arrays.asList(new PublisherRideDTO());
        when(publisherService.viewAllPublisherRides(anyInt())).thenReturn(rides);

        ResponseEntity<List<PublisherRideDTO>> response = publisherController.viewAllRides(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(publisherService, times(1)).viewAllPublisherRides(1);
    }
}
