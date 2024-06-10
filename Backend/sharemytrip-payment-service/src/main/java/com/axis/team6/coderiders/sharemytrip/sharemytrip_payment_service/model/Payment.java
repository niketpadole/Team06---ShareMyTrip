package com.axis.team6.coderiders.sharemytrip.sharemytrip_payment_service.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment1")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int publisherRideId;
    private int publisherId;
    private int passengerId;
    private BigDecimal totalAmount;
    private BigDecimal commissionAmount;
    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paymentDateTime;
    private String status;
}