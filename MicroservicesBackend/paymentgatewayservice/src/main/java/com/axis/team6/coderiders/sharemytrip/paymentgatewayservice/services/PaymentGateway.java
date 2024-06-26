package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.services;

import org.springframework.stereotype.Component;

import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.dtos.PaymentLinkRequestDto;
import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.models.PaymentStatus;

@Component
public interface PaymentGateway {
    String createPaymentLink(PaymentLinkRequestDto paymentLinkRequestDto);
    PaymentStatus getStatus(String paymentId, String orderId);
}
