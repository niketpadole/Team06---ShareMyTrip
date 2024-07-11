package com.axis.usermanagementservice.service;

import com.axis.usermanagementservice.dto.*;
import com.axis.usermanagementservice.entity.Publisher;
import com.axis.usermanagementservice.exception.PublisherNotFoundException;
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
class PublisherServiceImplTest {

    @InjectMocks
    private PublisherServiceImpl publisherService;

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
    void registerPublisher() {
        PublisherRegistrationRequest registrationRequest = new PublisherRegistrationRequest();
        registrationRequest.setEmail("test@test.com");
        registrationRequest.setPassword("password");

        Publisher publisher = new Publisher();
        publisher.setEmail("test@test.com");
        publisher.setPassword("password");

        when(publisherRepository.findByEmail(anyString())).thenReturn(null);
        when(modelMapper.map(any(PublisherRegistrationRequest.class), eq(Publisher.class))).thenReturn(publisher);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(restTemplate.postForEntity(anyString(), any(), eq(String.class))).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<?> response = publisherService.registerPublisher(registrationRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(publisherRepository, times(1)).save(any(Publisher.class));
    }

    @Test
    void loginPublisher() {
        String email = "test@test.com";
        String password = "password";
        Publisher publisher = new Publisher();
        publisher.setEmail(email);
        publisher.setPassword("encodedPassword");

        when(publisherRepository.findByEmail(anyString())).thenReturn(publisher);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(modelMapper.map(any(Publisher.class), eq(PublisherDTO.class))).thenReturn(new PublisherDTO());

        PublisherDTO result = publisherService.loginPublisher(email, password);

        assertNotNull(result);
        verify(publisherRepository, times(1)).findByEmail(email);
    }

    @Test
    void getAllPublishers() {
        List<Publisher> publishers = Arrays.asList(new Publisher());
        when(publisherRepository.findAll()).thenReturn(publishers);
        when(modelMapper.map(any(Publisher.class), eq(PublisherDTO.class))).thenReturn(new PublisherDTO());

        List<PublisherDTO> result = publisherService.getAllPublishers();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(publisherRepository, times(1)).findAll();
    }

    @Test
    void getPublisherById() {
        Publisher publisher = new Publisher();
        PublisherDTO publisherDTO = new PublisherDTO();
        when(publisherRepository.findById(anyInt())).thenReturn(Optional.of(publisher));
        when(modelMapper.map(any(Publisher.class), eq(PublisherDTO.class))).thenReturn(publisherDTO);

        PublisherDTO result = publisherService.getPublisherById(1);

        assertNotNull(result);
        verify(publisherRepository, times(1)).findById(1);
    }

//    @Test
//    void updatePublisher() {
//        PublisherDTO publisherDTO = new PublisherDTO();
//        publisherDTO.setFirstName("John");
//
//        Publisher publisher = new Publisher();
//        publisher.setFirstName("John");
//
//        when(publisherRepository.findById(anyInt())).thenReturn(Optional.of(publisher));
//        when(modelMapper.map(any(PublisherDTO.class), eq(Publisher.class))).thenReturn(publisher);
//        when(publisherRepository.save(any(Publisher.class))).thenReturn(publisher);
//
//        String result = publisherService.updatePublisher(46, new PublisherUpdateDTO());
//
//        assertEquals("Updated Successfully", result);
//        verify(publisherRepository, times(1)).save(any(Publisher.class));
//    }
//
//    @Test
//    void deletePublisher() {
//        doNothing().when(publisherRepository).deleteById(anyInt());
//
//        publisherService.deletePublisher(2);
//
//        verify(publisherRepository, times(2)).deleteById(2);
//    }

    @Test
    void createRide() {
        CreatePublisherRideDTO createPublisherRideDTO = new CreatePublisherRideDTO();
        when(restTemplate.postForObject(anyString(), any(), eq(String.class))).thenReturn("Ride created");

        String result = publisherService.createRide(createPublisherRideDTO);

        assertEquals("Ride created", result);
        verify(restTemplate, times(1)).postForObject(anyString(), any(), eq(String.class));
    }

    @Test
    void viewRidesById() {
        PublisherRideDTO[] rides = new PublisherRideDTO[]{new PublisherRideDTO()};
        when(restTemplate.getForObject(anyString(), eq(PublisherRideDTO[].class))).thenReturn(rides);

        List<PublisherRideDTO> result = publisherService.viewRidesById(1);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(PublisherRideDTO[].class));
    }

    @Test
    void setStatus() {
        doNothing().when(restTemplate).put(anyString(), any());

        publisherService.setStatus(1);

        verify(restTemplate, times(1)).put(anyString(), any());
    }

    @Test
    void viewNotCompletedRides() {
        PublisherRideDTO[] rides = new PublisherRideDTO[]{new PublisherRideDTO()};
        when(restTemplate.getForObject(anyString(), eq(PublisherRideDTO[].class))).thenReturn(rides);

        List<PublisherRideDTO> result = publisherService.viewNotCompletedRides(1);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(PublisherRideDTO[].class));
    }

    @Test
    void viewOngoingRides() {
        PublisherRideDTO[] rides = new PublisherRideDTO[]{new PublisherRideDTO()};
        when(restTemplate.getForObject(anyString(), eq(PublisherRideDTO[].class))).thenReturn(rides);

        List<PublisherRideDTO> result = publisherService.viewOngoingRides(1);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(PublisherRideDTO[].class));
    }

    @Test
    void viewPassengers() {
        RideDetailsDTO[] passengers = new RideDetailsDTO[]{new RideDetailsDTO()};
        when(restTemplate.getForObject(anyString(), eq(RideDetailsDTO[].class))).thenReturn(passengers);

        List<RideDetailsDTO> result = publisherService.viewPassengers(1);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(RideDetailsDTO[].class));
    }

    @Test
    void viewAllPublisherRides() {
        PublisherRideDTO[] rides = new PublisherRideDTO[]{new PublisherRideDTO()};
        when(restTemplate.getForObject(anyString(), eq(PublisherRideDTO[].class))).thenReturn(rides);

        List<PublisherRideDTO> result = publisherService.viewAllPublisherRides(1);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(PublisherRideDTO[].class));
    }

    @Test
    void setStatusEnd() {
        doNothing().when(restTemplate).put(anyString(), any());

        publisherService.setStatusEnd(1);

        verify(restTemplate, times(1)).put(anyString(), any());
    }
}
