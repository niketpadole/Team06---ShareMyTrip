package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.services;

import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.dtos.TransactionLinkRequestDto;
import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.models.PaymentStatus;

public interface TransactionGateway {
	String createPaymentLink(TransactionLinkRequestDto transactionLinkRequestDto);
    PaymentStatus getStatus(String paymentId, String orderId);
}
