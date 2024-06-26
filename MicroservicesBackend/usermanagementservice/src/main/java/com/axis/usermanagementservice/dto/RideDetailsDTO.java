package com.axis.usermanagementservice.dto;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideDetailsDTO {
	private Integer passengerId;
    private String publisherName;
    private Long publisherMobile;
    private String fromLocation;
    private String toLocation;
    private Integer noOfSeats;
    private Date date;
    private Time departureTime;
    private Float fare;
    private Float distance;
    private Float journeyHours;
    private String publisherStatus;
    private Integer publisherRideId;
    private Integer passengerRideId;
    private Integer passengerCount;
    private String passengerName;
    private String publisherPaymentStatus;
    private String passengerPaymentStatus;
    private String passengerMobile;
    
}
