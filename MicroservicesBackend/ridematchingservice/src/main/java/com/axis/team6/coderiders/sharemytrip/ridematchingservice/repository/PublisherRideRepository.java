package com.axis.team6.coderiders.sharemytrip.ridematchingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PublisherRide;

public interface PublisherRideRepository extends JpaRepository<PublisherRide, Integer> {
    List<PublisherRide> findByAvailableSeatsGreaterThan(int availableSeats);
    List<PublisherRide> findByFromLocationAndToLocation(String fromLocation, String toLocation);
}
