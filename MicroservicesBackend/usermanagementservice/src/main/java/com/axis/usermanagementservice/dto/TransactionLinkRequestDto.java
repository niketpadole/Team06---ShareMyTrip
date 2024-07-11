package com.axis.usermanagementservice.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import lombok.Data;

@Data
public class TransactionLinkRequestDto {
	private String orderId;
	
//	private Integer passengerId;
//    private Date dateOfJourney;
//    private Double totalFare;
//    private String publisherRideId;
//    private String passengerRideId;
//    private Integer publisherId;
//    private LocalDateTime departureTime;
//    private Integer reservedSeats;
//    private String passengerFirstName;
//    private String passengerLastName;
private LocalDateTime timestamp;
    private Integer passengerId;
    private Integer passengerRideId;
    private Integer publisherId;
    private Integer publisherRideId;
    private String publisherName;
    private Long publisherMobile;
    private String fromLocation;
    private String toLocation;
    private Date dateOfJourney;
    private Integer noOfSeats;
    private Date date;
    private LocalTime departureTime;
    private Float fare;//farePerSeat
    private Float distance;
    private String journeyHours;
    private String publisherStatus;
    private String publisherPaymentStatus;
    private String passengerPaymentStatus;
    private String passengerName;
    private String passengerEmail;
    private String passengerMobile;
    private Integer passengerCount;
	
    //private String token;
}
