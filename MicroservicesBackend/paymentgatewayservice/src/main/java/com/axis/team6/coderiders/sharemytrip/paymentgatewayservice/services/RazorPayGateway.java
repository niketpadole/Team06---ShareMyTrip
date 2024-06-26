package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.services;
import java.time.LocalDate;
import java.time.ZoneId;

import com.razorpay.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.dtos.TransactionLinkRequestDto;
import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.models.PaymentStatus;

@Service
public class RazorPayGateway implements TransactionGateway{

	@Autowired
    private final RazorpayClient razorpayClient;

    public RazorPayGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String createPaymentLink(TransactionLinkRequestDto transactionLinkRequestDto) {
         /*
           There are generally two ways to integrate with a 3rd party
           1. Make an api call
           2. Client sdk (Code in a jar)
        */

        JSONObject paymentLinkRequest = new JSONObject();

        //*********change this***********
        //FareCalculation
        paymentLinkRequest.put("amount",transactionLinkRequestDto.getPassengerCount()*transactionLinkRequestDto.getFare()*100);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("expire_by", LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
        paymentLinkRequest.put("reference_id",transactionLinkRequestDto.getOrderId());
        paymentLinkRequest.put("description","Payment for order no " + transactionLinkRequestDto.getOrderId());

        JSONObject customer = new JSONObject();
        customer.put("name",transactionLinkRequestDto.getPassengerName());
        customer.put("contact",transactionLinkRequestDto.getPassengerMobile());
        customer.put("email",transactionLinkRequestDto.getPassengerEmail());
        paymentLinkRequest.put("customer",customer);

        JSONObject notes = new JSONObject();
        //notes.put("policy_name","Jeevan Bima");
        notes.put("Passenger","paid for ride");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","http://localhost:5173/paymentSucess");
        paymentLinkRequest.put("callback_method","get");
        PaymentLink payment=null;
        try {
            payment = razorpayClient.paymentLink.create(paymentLinkRequest);

        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
        System.out.println("After link is created:"+payment.toString());
        return payment.get("short_url");
//        try {
////            PaymentLink  payment = razorpayClient.paymentLink.create(paymentLinkRequest);
//
////            return payment.get("short_url");
//                    Order order = razorpayClient.orders.create(paymentLinkRequest);
//            System.out.println("After link is created:"+order.toString());
//        return order.get("id");
//
//        } catch (RazorpayException e) {
//            //throw new RuntimeException("Failed to create payment link", e);
//        	return null;
//        }
    }

    @Override
    public PaymentStatus getStatus(String paymentId, String orderId) {

        try {
            Payment  payment = razorpayClient.payments.fetch(paymentId);
            String statusType = payment.get("status");
            return switch (statusType) {
                case "captured" -> PaymentStatus.SUCCESS;
                case "failed" -> PaymentStatus.FAILURE;
                default -> PaymentStatus.INITIATED;
            };
        } catch (RazorpayException e) {
            throw new RuntimeException("Unable to fetch the payment details", e);
        }
    }
}
