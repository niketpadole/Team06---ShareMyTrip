package com.axis.usermanagementservice.dto;

import java.sql.Date;
import java.sql.Time;

import lombok.Data;

@Data
public class PublisherRideDTO {
    private int publisherRideId;
    private int publisherId;
    private String fromLocation;
    private String toLocation;
    private float distance;
    private float journeyHours;
    private int availableSeats;
    private Date dateOfJourney;
    private Time timeOfJourney;
    private float farePerSeats;
    private String aboutRide;
    private java.sql.Timestamp timestamp;
}
