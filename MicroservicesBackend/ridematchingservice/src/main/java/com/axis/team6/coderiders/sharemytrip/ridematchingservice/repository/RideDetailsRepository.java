package com.axis.team6.coderiders.sharemytrip.ridematchingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.RideDetailsView;

@Repository
public interface RideDetailsRepository extends JpaRepository<RideDetailsView, String> {
	@Query(value = "SELECT * FROM ride_details_view WHERE passenger_id = :passengerId", nativeQuery = true)
    List<RideDetailsView> findRidesByPassengerId(@Param("passengerId") int passengerId);
	
	@Query(value = "SELECT * FROM ride_details_view WHERE publisher_ride_id = :publisherRideId", nativeQuery = true)
    List<RideDetailsView> findRidesByPublisherId(@Param("publisherRideId") int publisherRideId);
	
	@Query(value = "SELECT * FROM ride_details_view WHERE passenger_id = :passengerId and passenger_payment_status='PAID'", nativeQuery = true)
    List<RideDetailsView> findRidesByPassengerPaymentStatus(@Param("passengerId") int passengerId);

//	@Query(value = "SELECT * FROM ride_details_view WHERE passenger_id = :passengerId and publisher_status='NOT_COMPLETED'", nativeQuery = true)
//	List<RideDetailsView> findByPublisherStatusAndPassengerId(@Param("passengerId") Integer passengerId);
	  List<RideDetailsView> findByPublisherStatusAndPassengerId(String status, Integer passengerId);

}