package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.dtos;

import java.util.Date;

import com.mongodb.internal.connection.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDetailsDTO {
    private Integer passengerId;
    private String publisherName;
    private Long publisherMobile;
    private String fromLocation;
    private String toLocation;
    private Integer noOfSeats;
    private Date date;
    private Time departureTime;
    private Float fare;//farePerSeat
    private Float distance;
    private String journeyHours;
    private String publisherStatus;
    private Integer publisherRideId;
    private Integer passengerCount;
    private String publisherPaymentStatus;
    private String passengerPaymentStatus;
    private String passengerMobile;
    private String passengerEmail;
    private String publisherEmail;

}
