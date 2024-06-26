package com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor	
@Entity
@Table(name = "publisher_ride_table")
public class PublisherRide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_ride_id")
    private Integer publisherRideId;

    @Column(name = "publisher_id", nullable = false)
    private Integer publisherId;

    @Column(name = "from_location", nullable = false)
    private String fromLocation;

    @Column(name = "to_location", nullable = false)
    private String toLocation;

    @Column(name = "distance", nullable = false)
    private float distance;

    @Column(name = "journey_hours", nullable = false)
    private float journeyHours;

    @Column(name = "available_seats", nullable = false)
    private int availableSeats;

    @Column(name="reserved_seats")
    private int reservedSeats;
    
    @Column(name = "date_of_journey", nullable = false)
    private Date dateOfJourney;

    @Column(name = "time_of_journey", nullable = false)
    private Time timeOfJourney;

    @Column(name = "fare_per_seat", nullable = false)
    private float farePerSeat;

    @Column(name = "about_ride")
    private String aboutRide;

    @Column(name = "status")
    private String status;
    
    @Column(name = "payment_status")
    private String paymentStatus = "PENDING";
}
