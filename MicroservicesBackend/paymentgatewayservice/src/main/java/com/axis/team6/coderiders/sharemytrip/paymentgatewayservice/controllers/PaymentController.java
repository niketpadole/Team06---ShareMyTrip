package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.models.PaymentStatus;
import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.services.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
    PaymentService paymentService;

    PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/createLink")
    public String createPaymentLink(@RequestParam String orderId){
        return paymentService.createLink(orderId);
    }

    @GetMapping("/getPaymentStatus")
    public PaymentStatus getPaymentStatus(@RequestParam("paymentId") String paymentId, @RequestParam("orderId") String orderId){
        return paymentService.getPaymentStatus(paymentId, orderId);
    }
}
