package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Payments_table")
public class PaymentDetails extends BaseModel{
    private String orderId;
    private String paymentId;
    private String PaymentLink;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
