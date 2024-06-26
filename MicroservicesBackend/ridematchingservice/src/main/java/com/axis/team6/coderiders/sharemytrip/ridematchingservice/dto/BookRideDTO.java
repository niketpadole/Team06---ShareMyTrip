package com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class BookRideDTO {
    private Integer publisherRideId;
    private Integer passengerId;
    private int noOfPassengers;
    private String status="NOT_COMPLETED";
    private String paymentStatus = "PENDING";
}
