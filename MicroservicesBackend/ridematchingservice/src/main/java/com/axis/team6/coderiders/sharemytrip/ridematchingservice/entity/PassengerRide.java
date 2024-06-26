package com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity;

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
@Table(name = "passenger_ride_table")
public class PassengerRide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_ride_id")
    private Integer passengerRideId;

    @Column(name = "publisher_ride_id", nullable = false)
    private Integer publisherRideId;

    @Column(name = "passenger_id", nullable = false)
    private Integer passengerId;

    @Column(name = "no_of_passengers", nullable = false)
    private int noOfPassengers;

    @Column(name = "status")
    private String status = "NOT_COMPLETED";
    
    @Column(name = "payment_status")
    private String paymentStatus = "PENDING";
}
