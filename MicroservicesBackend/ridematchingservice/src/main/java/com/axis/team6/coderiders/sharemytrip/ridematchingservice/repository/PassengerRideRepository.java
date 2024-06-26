package com.axis.team6.coderiders.sharemytrip.ridematchingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PassengerRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.RideDetailsView;

@Repository
public interface PassengerRideRepository extends JpaRepository<PassengerRide, Integer> {
    List<PassengerRide> findByPassengerId(Integer passengerId);
    //PublisherRide findByPublisherRideId(Integer id);
    //@Query(value = "SELECT * FROM ride_details_view WHERE passenger_id = :passengerId", nativeQuery = true)
    //List<RideDetailsView> findRidesByPassengerId(@Param("passengerId") int passengerId);
		List<PassengerRide> findByPublisherRideId(Integer publisherRideId);
		List<PassengerRide> findAllByPublisherRideId(Integer id);
}
