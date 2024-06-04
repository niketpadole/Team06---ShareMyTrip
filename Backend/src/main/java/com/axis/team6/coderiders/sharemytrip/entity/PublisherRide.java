package com.axis.team6.coderiders.sharemytrip.entity;

import java.sql.Timestamp;

import jakarta.persistence.*;
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
    private int publisherRideId;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

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

    @Column(name = "date_of_journey", nullable = false)
    private java.sql.Date dateOfJourney;

    @Column(name = "time_of_journey", nullable = false)
    private java.sql.Time timeOfJourney;

    @Column(name = "fare_per_seats", nullable = false)
    private float farePerSeats;

    @Column(name = "about_ride")
    private String aboutRide;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

}
