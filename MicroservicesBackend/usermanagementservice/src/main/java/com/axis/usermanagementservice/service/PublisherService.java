package com.axis.usermanagementservice.service;

import java.util.List;

import com.axis.usermanagementservice.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.axis.usermanagementservice.entity.Publisher;

@Service
public interface PublisherService {
    ResponseEntity<?> registerPublisher(PublisherRegistrationRequest registrationRequest);
    PublisherDTO loginPublisher(String email, String password);
    List<PublisherDTO> getAllPublishers();
    PublisherDTO getPublisherById(int id);
    String updatePublisher(int id, PublisherUpdateDTO publisherDetails);
    void deletePublisher(int id);
    
    public String createRide(CreatePublisherRideDTO createPublisherRideDTO);
	List<PublisherRideDTO> viewRidesById(int id);
	void setStatus(Integer id);
	List<PublisherRideDTO> viewNotCompletedRides(Integer id);
	List<PublisherRideDTO> viewOngoingRides(int id);
	List<RideDetailsDTO> viewPassengers(int publisherRideId);
	List<PublisherRideDTO> viewAllPublisherRides(int publisherId);
	void setStatusEnd(Integer id);
    Publisher getPublisherByEmail(String Email);
    // void updatePassword(String email, String newPassword);
	void updatePassword(String email, String newPassword,String userType);

    void updateTotalEarnings(RideDetailsDTO rideDetailsDTO);

    List<TransactionDetailsDTO> getTransactions(Integer publisherRideId);

//    void allPassengersPaid(PublisherRideDTO p);
//    void allPassengersPaid(RideDetailsDTO rideDetailsDTO);
}
