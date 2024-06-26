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
@Table(name="publisher_ride_table")
public class PublisherRide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer publisherRideId;
    
    @Column(nullable = false)
    private Integer publisherId;
    
    @Column(nullable = false)
    private String fromLocation;
    
    @Column(nullable = false)
    private String toLocation;
    
    @Column(nullable = false)
    private float distance;
    
    @Column(nullable = false)
    private float journeyHours;
    
    @Column(nullable = false)
    private int availableSeats;
    
    @Column(nullable = false)
    private Date dateOfJourney;
    
    @Column(nullable = false)
    private Time timeOfJourney;
    
    @Column(nullable = false)
    private float farePerSeat;
    
    private String aboutRide;

}
