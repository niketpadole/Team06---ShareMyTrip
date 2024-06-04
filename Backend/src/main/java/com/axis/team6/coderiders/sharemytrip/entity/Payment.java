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
@Table(name = "payment_table")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int paymentId;

    @ManyToOne
    @JoinColumn(name = "publisher_ride_id", nullable = false)
    private PublisherRide publisherRide;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @Column(name = "total_amount", nullable = false)
    private float totalAmount;

    @Column(name = "commission_amount", nullable = false)
    private float commissionAmount;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

 
}
