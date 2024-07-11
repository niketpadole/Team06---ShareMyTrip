package com.axis.team6.coderiders.sharemytrip.ridematchingservice.controller;

import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.*;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PassengerRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PublisherRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.service.RideService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RideController.class)
class RideControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RideService rideService;

    private PublisherRideDTO publisherRideDTO;
    private RideDetailsDTO rideDetailsDTO;
    private PassengerRide passengerRide;
    private PublisherRide publisherRide;

    @BeforeEach
    void setUp() {
        publisherRideDTO = new PublisherRideDTO();
        publisherRideDTO.setPublisherRideId(1);

        rideDetailsDTO = new RideDetailsDTO();
        rideDetailsDTO.setPublisherRideId(1);

        passengerRide = new PassengerRide();
        passengerRide.setPassengerRideId(1);

        publisherRide = new PublisherRide();
        publisherRide.setPublisherRideId(1);
    }

    @Test
    void getAllCompletedRides() throws Exception {
        List<PublisherRideDTO> rides = Arrays.asList(publisherRideDTO);
        Mockito.when(rideService.getCompletedRidesDetails()).thenReturn(rides);

        mockMvc.perform(MockMvcRequestBuilders.get("/rides/completed"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'publisherRideId': 1}]"));
    }

    @Test
    void createPublisherRide() throws Exception {
        Mockito.when(rideService.createPublisherRide(any(CreatePublisherRideDTO.class))).thenReturn(publisherRide);

        mockMvc.perform(MockMvcRequestBuilders.post("/rides/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"publisherId\": 1, \"fromLocation\": \"LocationA\", \"toLocation\": \"LocationB\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{'publisherRideId': 1}"));
    }

    @Test
    void cancelPublishedRide() throws Exception {
        Mockito.doNothing().when(rideService).cancelPublishedRide(anyInt());

        mockMvc.perform(MockMvcRequestBuilders.delete("/rides/cancel-published/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getPublisherRidesDetails() throws Exception {
        List<PublisherRideDTO> rides = Arrays.asList(publisherRideDTO);
        Mockito.when(rideService.getPublisherRidesDetails(anyInt())).thenReturn(rides);

        mockMvc.perform(MockMvcRequestBuilders.get("/rides/publisher/1/rides"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'publisherRideId': 1}]"));
    }

    @Test
    void getCompletedRides() throws Exception {
        List<PublisherRideDTO> rides = Arrays.asList(publisherRideDTO);
        Mockito.when(rideService.getCompletedRides(anyInt())).thenReturn(rides);

        mockMvc.perform(MockMvcRequestBuilders.get("/rides/publisher/1/completed"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'publisherRideId': 1}]"));
    }

    @Test
    void setRideStatus() throws Exception {
        Mockito.when(rideService.setRideStatus(anyInt())).thenReturn("success");

        mockMvc.perform(MockMvcRequestBuilders.put("/rides/publisher/1/start"))
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
    }

    @Test
    void setRideStatusEnd() throws Exception {
        Mockito.when(rideService.setRideStatusEnd(anyInt())).thenReturn("success");

        mockMvc.perform(MockMvcRequestBuilders.put("/rides/publisher/1/end"))
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
    }

    @Test
    void getNotCompletedRides() throws Exception {
        List<PublisherRideDTO> rides = Arrays.asList(publisherRideDTO);
        Mockito.when(rideService.getNotCompletedRides(anyInt())).thenReturn(rides);

        mockMvc.perform(MockMvcRequestBuilders.get("/rides/publisher/1/not-completed"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'publisherRideId': 1}]"));
    }

    @Test
    void getOngoingRides() throws Exception {
        List<PublisherRideDTO> rides = Arrays.asList(publisherRideDTO);
        Mockito.when(rideService.getOngoingRides(anyInt())).thenReturn(rides);

        mockMvc.perform(MockMvcRequestBuilders.get("/rides/publisher/1/ongoing"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'publisherRideId': 1}]"));
    }

    @Test
    void viewPassengers() throws Exception {
        List<RideDetailsDTO> rides = Arrays.asList(rideDetailsDTO);
        Mockito.when(rideService.viewPassengers(anyInt())).thenReturn(rides);

        mockMvc.perform(MockMvcRequestBuilders.get("/rides/publisher/1/passengers"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'publisherRideId': 1}]"));
    }

    @Test
    void bookRide() throws Exception {
        Mockito.when(rideService.bookRide(any(BookRideDTO.class))).thenReturn(passengerRide);

        mockMvc.perform(MockMvcRequestBuilders.post("/rides/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"passengerId\": 1, \"publisherRideId\": 1}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{'passengerRideId': 1}"));
    }

    @Test
    void viewAvailableRides() throws Exception {
        List<PublisherRideDTO> rides = Arrays.asList(publisherRideDTO);
        Mockito.when(rideService.viewAvailableRides()).thenReturn(rides);

        mockMvc.perform(MockMvcRequestBuilders.get("/rides/available"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'publisherRideId': 1}]"));
    }

    @Test
    void cancelRide() throws Exception {
        Mockito.doNothing().when(rideService).cancelRide(anyInt());

        mockMvc.perform(MockMvcRequestBuilders.delete("/rides/cancel/1"))
                .andExpect(status().isNoContent());
    }

//    @Test
//    void getFilteredRides() throws Exception {
//        List<PublisherRideDTO> rides = Arrays.asList(publisherRideDTO);
//        Mockito.when(rideService.getFilteredRides(anyString(), anyString(), any(Date.class))).thenReturn(rides);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/rides/filter")
//                        .param("fromLocation", "LocationA")
//                        .param("toLocation", "LocationB")
//                        .param("dateOfJourney", "2023-06-22"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("[{'publisherRideId': 1}]"));
//    }

    @Test
    void getPassengerRidesDetails() throws Exception {
        List<RideDetailsDTO> rides = Arrays.asList(rideDetailsDTO);
        Mockito.when(rideService.getPassengerRidesDetails(anyInt())).thenReturn(rides);

        mockMvc.perform(MockMvcRequestBuilders.get("/rides/passenger/1/rides"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'publisherRideId': 1}]"));
    }

    @Test
    void viewPastRides() throws Exception {
        List<RideDetailsDTO> rides = Arrays.asList(rideDetailsDTO);
        Mockito.when(rideService.viewPastRides(anyInt())).thenReturn(rides);

        mockMvc.perform(MockMvcRequestBuilders.get("/rides/passenger/1/past-rides"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'publisherRideId': 1}]"));
    }

    @Test
    void getNotCompletedRidesPassenger() throws Exception {
        List<RideDetailsDTO> rides = Arrays.asList(rideDetailsDTO);
        Mockito.when(rideService.getNotCompletedRidesPassenger(anyInt())).thenReturn(rides);

        mockMvc.perform(MockMvcRequestBuilders.get("/rides/passenger/1/not-completed"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'publisherRideId': 1}]"));
    }

    @Test
    void getOngoingRidesPassenger() throws Exception {
        List<RideDetailsDTO> rides = Arrays.asList(rideDetailsDTO);
        Mockito.when(rideService.getOngoingRidesPassenger(anyInt())).thenReturn(rides);

        mockMvc.perform(MockMvcRequestBuilders.get("/rides/passenger/1/ongoing"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'publisherRideId': 1}]"));
    }

    @Test
    void setRideStatusPassenger() throws Exception {
        Mockito.when(rideService.setRideStatusPassenger(anyInt())).thenReturn("success");

        mockMvc.perform(MockMvcRequestBuilders.put("/rides/passenger/1/paid"))
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
    }
}
