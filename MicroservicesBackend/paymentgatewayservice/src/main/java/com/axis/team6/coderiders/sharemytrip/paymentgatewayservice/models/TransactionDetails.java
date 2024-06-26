package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transactions")
public class TransactionDetails extends BaseModel {
	private String orderId;
	private String paymentId;

	@Field("paymentLink")
	private String paymentLink;

	private PaymentStatus status;

	private Integer passengerId;
	private Date dateOfJourney;
	private Double totalFare;
	private Integer publisherRideId;
	private Integer passengerRideId;
	private Integer publisherId;
	private LocalTime departureTime;
	private Integer reservedSeats;
	private String passengerName;
	private LocalDateTime timestamp;
}
