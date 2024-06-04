package com.axis.team6.coderiders.sharemytrip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "distance_table")
public class Distance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "distance_id")
    private int distanceId;

    @Column(name = "from_location", nullable = false)
    private String fromLocation;

    @Column(name = "to_location", nullable = false)
    private String toLocation;

    @Column(name = "distance", nullable = false)
    private float distance;

    @Column(name = "fare_per_seats", nullable = false)
    private float farePerSeats;

  
}

