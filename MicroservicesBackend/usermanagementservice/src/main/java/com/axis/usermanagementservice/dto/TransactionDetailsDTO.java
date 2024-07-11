package com.axis.usermanagementservice.dto;

import lombok.Data;
import com.axis.usermanagementservice.entity.PaymentStatus;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
public class TransactionDetailsDTO {

    private String orderId;
    private String paymentId;
    private String paymentLink;
    private PaymentStatus status;
    private Integer passengerId;
    private Date dateOfJourney;
    private Float totalFare;
    private Integer publisherRideId;
    private Integer passengerRideId;
    private Integer publisherId;
    private LocalTime departureTime;
    private Integer reservedSeats;
    private String passengerName;
    private LocalDateTime timestamp;
}
