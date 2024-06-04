package com.axis.team6.coderiders.sharemytrip.entity;

import jakarta.persistence.*;
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
    private int passengerRideId;

    @ManyToOne
    @JoinColumn(name = "publisher_ride_id", nullable = false)
    private PublisherRide publisherRide;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @Column(name = "no_of_passengers", nullable = false)
    private int noOfPassengers;

}
