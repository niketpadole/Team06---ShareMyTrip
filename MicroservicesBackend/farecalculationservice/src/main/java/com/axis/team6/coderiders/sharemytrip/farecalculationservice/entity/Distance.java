package com.axis.team6.coderiders.sharemytrip.farecalculationservice.entity;


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
