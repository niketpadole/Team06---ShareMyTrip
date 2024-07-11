package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.dtos.TransactionDetailsDTO;
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
        paymentResponse.setTotalFare(dto.getFare());
        transactionRepository.save(paymentResponse);

        // Return payment link
        return paymentLink;
    }

    public List<TransactionDetailsDTO> getTransactions(Integer passengerId) {
        List<TransactionDetails> list=transactionRepository.findByPassengerId(passengerId);
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private TransactionDetailsDTO convertToDto(TransactionDetails transactionDetails) {
        TransactionDetailsDTO dto = new TransactionDetailsDTO();
        dto.setOrderId(transactionDetails.getOrderId());
        dto.setPaymentId(transactionDetails.getPaymentId());
        dto.setPaymentLink(transactionDetails.getPaymentLink());
        dto.setStatus(transactionDetails.getStatus());
        dto.setPassengerId(transactionDetails.getPassengerId());
        dto.setDateOfJourney(transactionDetails.getDateOfJourney());
        dto.setTotalFare(transactionDetails.getTotalFare());
        dto.setPublisherRideId(transactionDetails.getPublisherRideId());
        dto.setPassengerRideId(transactionDetails.getPassengerRideId());
        dto.setPublisherId(transactionDetails.getPublisherId());
        dto.setDepartureTime(transactionDetails.getDepartureTime());
        dto.setReservedSeats(transactionDetails.getReservedSeats());
        dto.setPassengerName(transactionDetails.getPassengerName());
        dto.setTimestamp(transactionDetails.getTimestamp());
        return dto;
    }

    public List<TransactionDetailsDTO> getPassengerTransactions(Integer publisherRideId) {
        List<TransactionDetails> list=transactionRepository.findByPublisherRideId(publisherRideId);
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }
}
