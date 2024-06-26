package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.dtos.TransactionLinkRequestDto;
import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.models.TransactionDetails;
import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.repositories.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionGateway transactionGateway;

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionGateway transactionGateway, TransactionRepository transactionRepository) {
        this.transactionGateway = transactionGateway;
        this.transactionRepository = transactionRepository;
    }

    public String createTransaction(TransactionLinkRequestDto dto) {
        // Generate payment link using the payment gateway
        String paymentLink = transactionGateway.createPaymentLink(dto);

        // Save in collection
        TransactionDetails paymentResponse = new TransactionDetails();
        paymentResponse.setPaymentLink(paymentLink);
        paymentResponse.setOrderId(dto.getOrderId());
        paymentResponse.setPublisherId(dto.getPublisherId());
        paymentResponse.setPublisherRideId(dto.getPublisherRideId());
        paymentResponse.setPassengerId(dto.getPassengerId());
        paymentResponse.setPassengerRideId(dto.getPassengerRideId());
        paymentResponse.setReservedSeats(dto.getPassengerCount());
        paymentResponse.setPassengerName(dto.getPassengerName());
        paymentResponse.setTimestamp(LocalDateTime.now());
        transactionRepository.save(paymentResponse);

        // Return payment link
        return paymentLink;
    }
}
