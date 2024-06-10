package com.axis.team6.coderiders.sharemytrip.ridematchingservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.BookRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.CreatePublisherRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.PublisherRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PassengerRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PublisherRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.exception.RideNotFoundException;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.repository.PassengerRideRepository;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.repository.PublisherRideRepository;

@Service

public class RideServiceImpl implements RideService
 {
    @Autowired
    private PublisherRideRepository publisherRideRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PassengerRideRepository passengerRideRepository;

    public PublisherRide createPublisherRide(CreatePublisherRideDTO createPublisherRideDTO) {
        PublisherRide publisherRide = new PublisherRide();
        BeanUtils.copyProperties(createPublisherRideDTO, publisherRide);
        return publisherRideRepository.save(publisherRide);
    }

    public PassengerRide bookRide(BookRideDTO bookRideDTO) {
        PassengerRide passengerRide = new PassengerRide();
        BeanUtils.copyProperties(bookRideDTO, passengerRide);
        PublisherRide publisherRide = publisherRideRepository.findById(bookRideDTO.getPublisherRideId()).orElseThrow(() -> new RuntimeException("Publisher Ride not found"));
        if (publisherRide.getAvailableSeats() < bookRideDTO.getNoOfPassengers()) {
            throw new RuntimeException("Not enough available seats");
        }
        publisherRide.setAvailableSeats(publisherRide.getAvailableSeats() - bookRideDTO.getNoOfPassengers());
        publisherRideRepository.save(publisherRide);
        return passengerRideRepository.save(passengerRide);
    }

    public List<PublisherRide> viewAvailableRides() {
        return publisherRideRepository.findByAvailableSeatsGreaterThan(0);
    }

    public void cancelRide(Integer passengerRideId) {
        PassengerRide passengerRide = passengerRideRepository.findById(passengerRideId).orElseThrow(() -> new RuntimeException("Passenger Ride not found"));
        PublisherRide publisherRide = publisherRideRepository.findById(passengerRide.getPublisherRideId()).orElseThrow(() -> new RuntimeException("Publisher Ride not found"));
        publisherRide.setAvailableSeats(publisherRide.getAvailableSeats() + passengerRide.getNoOfPassengers());
        publisherRideRepository.save(publisherRide);
        passengerRideRepository.deleteById(passengerRideId);
    }

    public void cancelPublishedRide(Integer publisherRideId) {
        PublisherRide publisherRide = publisherRideRepository.findById(publisherRideId).orElseThrow(() -> new RuntimeException("Publisher Ride not found"));
        publisherRideRepository.deleteById(publisherRideId);
        List<PassengerRide> passengerRides = passengerRideRepository.findByPublisherRideId(publisherRideId);
        for (PassengerRide passengerRide : passengerRides) {
            passengerRideRepository.deleteById(passengerRide.getPassengerRideId());
        }
    }
    
    @Override
    public List<PublisherRideDTO> getPassengerRides(int passengerId) {
        List<PassengerRide> passengerRides = passengerRideRepository.findByPassengerId(passengerId);
        return passengerRides.stream()
                .map(pr -> {
                    PublisherRide publisherRide = publisherRideRepository.findById(pr.getPublisherRideId()).orElse(null);
                    return modelMapper.map(publisherRide, PublisherRideDTO.class);
                })
                .collect(Collectors.toList());
    }
    
    //filter rides according to ToLocation and FromLocation
    @Override
    public List<PublisherRideDTO> getFilteredRides(String fromLocation, String toLocation) {
        List<PublisherRide> publisherRides = publisherRideRepository.findByFromLocationAndToLocation(fromLocation, toLocation);
        return publisherRides.stream()
                .map(ride -> modelMapper.map(ride, PublisherRideDTO.class))
                .collect(Collectors.toList());
    }
    
    //cancel ride by passenger
    public void cancelRide(int passengerRideId) {
        PassengerRide passengerRide = passengerRideRepository.findById(passengerRideId)
                .orElseThrow(() -> new RideNotFoundException("Passenger ride not found with id: " + passengerRideId));
        passengerRideRepository.delete(passengerRide);
    }
}

