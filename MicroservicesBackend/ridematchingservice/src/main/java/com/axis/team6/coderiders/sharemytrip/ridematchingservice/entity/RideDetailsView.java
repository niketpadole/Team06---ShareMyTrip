package com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity;


import java.sql.Time;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ride_details_view")
public class RideDetailsView {
	@Id
	@Column(name="ride_details_key")
	private String rideDetailsKey;

    @Column(name = "passenger_id")
    private Integer passengerId;
    
    @Column(name = "publisher_id")
    private Integer publisherId;

    @Column(name = "publisher_name")
    private String publisherName;

    @Column(name = "passenger_name")
    private String passengerName;

    @Column(name = "publisher_mobile")
    private Long publisherMobile;

    @Column(name = "from_location")
    private String fromLocation;

    @Column(name = "to_location")
    private String toLocation;

    @Column(name = "no_of_seats")
    private Integer noOfSeats;

    @Column(name = "date")
    private Date date;

    @Column(name = "departure_time")
    private Time departureTime;

    @Column(name = "fare")
    private Float fare;

    @Column(name = "distance")
    private Float distance;

    @Column(name = "journey_hours")
    private Float journeyHours;
    
    @Column(name = "publisher_status")
    private String publisherStatus;
    
    @Column(name = "publisher_ride_id")
    private Integer publisherRideId;
    
    @Column(name="passenger_ride_id")
    private Integer passengerRideId;
    
    @Column(name = "passenger_count")
    private Integer passengerCount;
    
    @Column(name="publisher_payment_status")
    private String publisherPaymentStatus;
    
    @Column(name="passenger_payment_status")
    private String passengerPaymentStatus;
    
    @Column(name="passenger_mobile")
    private String passengerMobile;
    
}
