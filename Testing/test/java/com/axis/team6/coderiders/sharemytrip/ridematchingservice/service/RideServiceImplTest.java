package com.axis.team6.coderiders.sharemytrip.ridematchingservice.service;

import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.BookRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.CreatePublisherRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.PublisherRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.RideDetailsDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PassengerRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PublisherRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.RideDetailsView;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.repository.PassengerRideRepository;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.repository.PublisherRideRepository;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.repository.RideDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RideServiceImplTest {

    @Mock
    private PublisherRideRepository publisherRideRepository;

    @Mock
    private PassengerRideRepository passengerRideRepository;

    @Mock
    private RideDetailsRepository rideDetailsRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private RideServiceImpl rideServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPublisherRide() {
        CreatePublisherRideDTO createPublisherRideDTO = new CreatePublisherRideDTO();
        PublisherRide publisherRide = new PublisherRide();
        when(publisherRideRepository.save(any(PublisherRide.class))).thenReturn(publisherRide);

        PublisherRide result = rideServiceImpl.createPublisherRide(createPublisherRideDTO);

        assertNotNull(result);
        verify(publisherRideRepository, times(1)).save(any(PublisherRide.class));
    }

    @Test
    void bookRide() {
        BookRideDTO bookRideDTO = new BookRideDTO();
        bookRideDTO.setPublisherRideId(1);
        bookRideDTO.setNoOfPassengers(2);

        PublisherRide publisherRide = new PublisherRide();
        publisherRide.setAvailableSeats(4);

        when(publisherRideRepository.findById(anyInt())).thenReturn(Optional.of(publisherRide));
        when(passengerRideRepository.save(any(PassengerRide.class))).thenReturn(new PassengerRide());

        PassengerRide result = rideServiceImpl.bookRide(bookRideDTO);

        assertNotNull(result);
        verify(publisherRideRepository, times(1)).save(any(PublisherRide.class));
        verify(passengerRideRepository, times(1)).save(any(PassengerRide.class));
    }

    @Test
    void bookRideNotEnoughSeats() {
        BookRideDTO bookRideDTO = new BookRideDTO();
        bookRideDTO.setPublisherRideId(1);
        bookRideDTO.setNoOfPassengers(5);

        PublisherRide publisherRide = new PublisherRide();
        publisherRide.setAvailableSeats(4);

        when(publisherRideRepository.findById(anyInt())).thenReturn(Optional.of(publisherRide));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            rideServiceImpl.bookRide(bookRideDTO);
        });

        String expectedMessage = "Not enough available seats";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(publisherRideRepository, never()).save(any(PublisherRide.class));
        verify(passengerRideRepository, never()).save(any(PassengerRide.class));
    }

    @Test
    void viewAvailableRides() {
        List<PublisherRide> rides = new ArrayList<>();
        rides.add(new PublisherRide());

        when(publisherRideRepository.findByAvailableSeatsGreaterThan(0)).thenReturn(rides);
        when(modelMapper.map(any(PublisherRide.class), eq(PublisherRideDTO.class))).thenReturn(new PublisherRideDTO());

        List<PublisherRideDTO> result = rideServiceImpl.viewAvailableRides();

        assertFalse(result.isEmpty());
        verify(publisherRideRepository, times(1)).findByAvailableSeatsGreaterThan(0);
    }

//    @Test
//    void cancelRide() {
//        PassengerRide passengerRide = new PassengerRide();
//        passengerRide.setNoOfPassengers(2);
//        passengerRide.setPublisherRideId(1);
//
//        PublisherRide publisherRide = new PublisherRide();
//        publisherRide.setAvailableSeats(4);
//
//        when(passengerRideRepository.findById(anyInt())).thenReturn(Optional.of(passengerRide));
//        when(publisherRideRepository.findById(anyInt())).thenReturn(Optional.of(publisherRide));
//
//        rideServiceImpl.cancelRide(1);
//
//        verify(publisherRideRepository, times(1)).save(any(PublisherRide.class));
//        verify(passengerRideRepository, times(1)).deleteById(anyInt());
//    }

//    @Test
//    void cancelPublishedRide() {
//        PublisherRide publisherRide = new PublisherRide();
//        List<PassengerRide> passengerRides = new ArrayList<>();
//        passengerRides.add(new PassengerRide());
//
//        when(publisherRideRepository.findById(anyInt())).thenReturn(Optional.of(publisherRide));
//        when(passengerRideRepository.findByPublisherRideId(anyInt())).thenReturn(passengerRides);
//
//        rideServiceImpl.cancelPublishedRide(1);
//
//        verify(publisherRideRepository, times(1)).deleteById(anyInt());
//        verify(passengerRideRepository, times(1)).findByPublisherRideId(anyInt());
//        verify(passengerRideRepository, times(1)).deleteById(anyInt());
//    }

    @Test
    void getPassengerRidesDetails() {
        List<RideDetailsView> rides = new ArrayList<>();
        rides.add(new RideDetailsView());

        when(rideDetailsRepository.findRidesByPassengerId(anyInt())).thenReturn(rides);
        when(modelMapper.map(any(RideDetailsView.class), eq(RideDetailsDTO.class))).thenReturn(new RideDetailsDTO());

        List<RideDetailsDTO> result = rideServiceImpl.getPassengerRidesDetails(1);

        assertFalse(result.isEmpty());
        verify(rideDetailsRepository, times(1)).findRidesByPassengerId(anyInt());
    }

    @Test
    void getFilteredRides() {
        List<PublisherRide> rides = new ArrayList<>();
        rides.add(new PublisherRide());

        when(publisherRideRepository.findByFromLocationAndToLocationAndDateOfJourney(anyString(), anyString(), any(Date.class))).thenReturn(rides);
        when(modelMapper.map(any(PublisherRide.class), eq(PublisherRideDTO.class))).thenReturn(new PublisherRideDTO());

        List<PublisherRideDTO> result = rideServiceImpl.getFilteredRides("New York", "Boston", new Date(System.currentTimeMillis()));

        assertFalse(result.isEmpty());
        verify(publisherRideRepository, times(1)).findByFromLocationAndToLocationAndDateOfJourney(anyString(), anyString(), any(Date.class));
    }

    @Test
    void getPublisherRidesDetails() {
        List<PublisherRide> rides = new ArrayList<>();
        rides.add(new PublisherRide());

        when(publisherRideRepository.findByPublisherId(anyInt())).thenReturn(rides);
        when(modelMapper.map(any(PublisherRide.class), eq(PublisherRideDTO.class))).thenReturn(new PublisherRideDTO());

        List<PublisherRideDTO> result = rideServiceImpl.getPublisherRidesDetails(1);

        assertFalse(result.isEmpty());
        verify(publisherRideRepository, times(1)).findByPublisherId(anyInt());
    }

    @Test
    void getCompletedRidesDetails() {
        List<PublisherRide> rides = new ArrayList<>();
        rides.add(new PublisherRide());

        when(publisherRideRepository.findByStatus(anyString())).thenReturn(Optional.of(rides));
        when(modelMapper.map(any(PublisherRide.class), eq(PublisherRideDTO.class))).thenReturn(new PublisherRideDTO());

        List<PublisherRideDTO> result = rideServiceImpl.getCompletedRidesDetails();

        assertFalse(result.isEmpty());
        verify(publisherRideRepository, times(1)).findByStatus(anyString());
    }

    @Test
    void getCompletedRides() {
        List<PublisherRide> rides = new ArrayList<>();
        rides.add(new PublisherRide());

        when(publisherRideRepository.findByStatusAndPublisherId(anyString(), anyInt())).thenReturn(Optional.of(rides));
        when(modelMapper.map(any(PublisherRide.class), eq(PublisherRideDTO.class))).thenReturn(new PublisherRideDTO());

        List<PublisherRideDTO> result = rideServiceImpl.getCompletedRides(1);

        assertFalse(result.isEmpty());
        verify(publisherRideRepository, times(1)).findByStatusAndPublisherId(anyString(), anyInt());
    }

    @Test
    void setRideStatus() {
        PublisherRide publisherRide = new PublisherRide();
        publisherRide.setStatus("NOT_COMPLETED");

        when(publisherRideRepository.findById(anyInt())).thenReturn(Optional.of(publisherRide));
        when(passengerRideRepository.findAllByPublisherRideId(anyInt())).thenReturn(new ArrayList<>());

        String result = rideServiceImpl.setRideStatus(1);

        assertEquals("success", result);
        assertEquals("ONGOING", publisherRide.getStatus());
        verify(publisherRideRepository, times(1)).save(any(PublisherRide.class));
    }

    @Test
    void getNotCompletedRides() {
        List<PublisherRide> rides = new ArrayList<>();
        rides.add(new PublisherRide());

        when(publisherRideRepository.findByStatusAndPublisherId(anyString(), anyInt())).thenReturn(Optional.of(rides));
        when(modelMapper.map(any(PublisherRide.class), eq(PublisherRideDTO.class))).thenReturn(new PublisherRideDTO());

        List<PublisherRideDTO> result = rideServiceImpl.getNotCompletedRides(1);

        assertFalse(result.isEmpty());
        verify(publisherRideRepository, times(1)).findByStatusAndPublisherId(anyString(), anyInt());
    }

    @Test
    void getOngoingRides() {
        List<PublisherRide> rides = new ArrayList<>();
        rides.add(new PublisherRide());

        when(publisherRideRepository.findByStatusAndPublisherId(anyString(), anyInt())).thenReturn(Optional.of(rides));
        when(modelMapper.map(any(PublisherRide.class), eq(PublisherRideDTO.class))).thenReturn(new PublisherRideDTO());

        List<PublisherRideDTO> result = rideServiceImpl.getOngoingRides(1);

        assertFalse(result.isEmpty());
        verify(publisherRideRepository, times(1)).findByStatusAndPublisherId(anyString(), anyInt());
    }

    @Test
    void viewPassengers() {
        List<RideDetailsView> rides = new ArrayList<>();
        rides.add(new RideDetailsView());

        when(rideDetailsRepository.findRidesByPublisherId(anyInt())).thenReturn(rides);
        when(modelMapper.map(any(RideDetailsView.class), eq(RideDetailsDTO.class))).thenReturn(new RideDetailsDTO());

        List<RideDetailsDTO> result = rideServiceImpl.viewPassengers(1);

        assertFalse(result.isEmpty());
        verify(rideDetailsRepository, times(1)).findRidesByPublisherId(anyInt());
    }

    @Test
    void viewPastRides() {
        List<RideDetailsView> rides = new ArrayList<>();
        rides.add(new RideDetailsView());

        when(rideDetailsRepository.findRidesByPassengerPaymentStatus(anyInt())).thenReturn(rides);
        when(modelMapper.map(any(RideDetailsView.class), eq(RideDetailsDTO.class))).thenReturn(new RideDetailsDTO());

        List<RideDetailsDTO> result = rideServiceImpl.viewPastRides(1);

        assertFalse(result.isEmpty());
        verify(rideDetailsRepository, times(1)).findRidesByPassengerPaymentStatus(anyInt());
    }

    @Test
    void getNotCompletedRidesPassenger() {
        List<RideDetailsView> rides = new ArrayList<>();
        rides.add(new RideDetailsView());

        when(rideDetailsRepository.findByPublisherStatusAndPassengerId(anyString(), anyInt())).thenReturn(rides);
        when(modelMapper.map(any(RideDetailsView.class), eq(RideDetailsDTO.class))).thenReturn(new RideDetailsDTO());

        List<RideDetailsDTO> result = rideServiceImpl.getNotCompletedRidesPassenger(1);

        assertFalse(result.isEmpty());
        verify(rideDetailsRepository, times(1)).findByPublisherStatusAndPassengerId(anyString(), anyInt());
    }

    @Test
    void getOngoingRidesPassenger() {
        List<RideDetailsView> rides = new ArrayList<>();
        rides.add(new RideDetailsView());

        when(rideDetailsRepository.findByPublisherStatusAndPassengerId(anyString(), anyInt())).thenReturn(rides);
        when(modelMapper.map(any(RideDetailsView.class), eq(RideDetailsDTO.class))).thenReturn(new RideDetailsDTO());

        List<RideDetailsDTO> result = rideServiceImpl.getOngoingRidesPassenger(1);

        assertFalse(result.isEmpty());
        verify(rideDetailsRepository, times(1)).findByPublisherStatusAndPassengerId(anyString(), anyInt());
    }

    @Test
    void setRideStatusPassenger() {
        PassengerRide passengerRide = new PassengerRide();
        passengerRide.setStatus("NOT_COMPLETED");

        when(passengerRideRepository.findById(anyInt())).thenReturn(Optional.of(passengerRide));

        String result = rideServiceImpl.setRideStatusPassenger(1);

        assertEquals("success", result);
        assertEquals("COMPLETED", passengerRide.getStatus());
        assertEquals("PAID", passengerRide.getPaymentStatus());
        verify(passengerRideRepository, times(1)).save(any(PassengerRide.class));
    }

    @Test
    void setRideStatusEnd() {
        PublisherRide publisherRide = new PublisherRide();
        publisherRide.setStatus("ONGOING");

        when(publisherRideRepository.findById(anyInt())).thenReturn(Optional.of(publisherRide));
        when(passengerRideRepository.findAllByPublisherRideId(anyInt())).thenReturn(new ArrayList<>());

        String result = rideServiceImpl.setRideStatusEnd(1);

        assertEquals("success", result);
        assertEquals("COMPLETED", publisherRide.getStatus());
        verify(publisherRideRepository, times(1)).save(any(PublisherRide.class));
    }
}
