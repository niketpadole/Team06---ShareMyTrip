package com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import lombok.Data;
@Data
public class CreatePublisherRideDTO {
    private Integer publisherId;
    private String fromLocation;
    private String toLocation;
    private float distance;
    private String journeyHours;
    private int availableSeats;
    private Date dateOfJourney;
    private Time timeOfJourney;
    private float farePerSeat;
    private String aboutRide;
    private String status="NOT_COMPLETED";
    private String paymentStatus = "PENDING";
}
