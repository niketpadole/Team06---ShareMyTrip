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
@Table(name="passenger_ride_table")
public class PassengerRide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer passengerRideId;
    
    @Column(nullable = false)
    private Integer publisherRideId;
    
    @Column(nullable = false)
    private Integer passengerId;
    
    @Column(nullable = false)
    private int noOfPassengers;

}
