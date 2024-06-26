package com.axis.team6.coderiders.sharemytrip.ridematchingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PassengerRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PublisherRide;

public interface PassengerRideRepository extends JpaRepository<PassengerRide, Integer> {
    List<PassengerRide> findByPassengerId(Integer passengerId);
    //PublisherRide findByPublisherRideId(Integer id);

	List<PassengerRide> findByPublisherRideId(Integer publisherRideId);
}
