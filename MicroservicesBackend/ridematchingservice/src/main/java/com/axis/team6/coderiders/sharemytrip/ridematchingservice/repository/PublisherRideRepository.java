package com.axis.team6.coderiders.sharemytrip.ridematchingservice.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.PublisherRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PublisherRide;

public interface PublisherRideRepository extends JpaRepository<PublisherRide, Integer> {
    List<PublisherRide> findByAvailableSeatsGreaterThan(int availableSeats);
    List<PublisherRide> findByFromLocationAndToLocationAndDateOfJourney(String fromLocation, String toLocation,Date dateOfJourney);
	Optional<List<PublisherRide>> findByStatus(String string);
	Optional<List<PublisherRide>> findByStatusAndPublisherRideId(String string, @Valid Integer id);
	Optional<List<PublisherRide>> findByStatusAndPublisherId(String string, @Valid Integer id);
	List<PublisherRide> findByPublisherId(Integer publisherId);
	
}
