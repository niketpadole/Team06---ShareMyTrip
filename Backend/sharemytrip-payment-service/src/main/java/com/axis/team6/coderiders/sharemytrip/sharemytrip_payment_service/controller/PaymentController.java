package com.axis.team6.coderiders.sharemytrip.sharemytrip_payment_service.controller;

import java.security.SignatureException;
import java.time.LocalDateTime;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axis.team6.coderiders.sharemytrip.sharemytrip_payment_service.model.Payment;
import com.axis.team6.coderiders.sharemytrip.sharemytrip_payment_service.repository.PaymentRepository;
import com.axis.team6.coderiders.sharemytrip.sharemytrip_payment_service.util.Signature;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class PaymentController {
	private static final String RAZORPAY_KEY = "rzp_test_sZs1uDS8hbm2Dj";
    private static final String RAZORPAY_SECRET = "8fOROlU5EeqePEIJTpCmeTQX";

    @Autowired
    PaymentRepository paymentRepository;

    @PostMapping("/create_order")
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> data) {
        try {
            int totalAmount = Integer.parseInt(data.get("totalAmount").toString());
            RazorpayClient razorpayClient = new RazorpayClient(RAZORPAY_KEY, RAZORPAY_SECRET);

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", totalAmount * 100); // amount in paise
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "rec_" + data.get("publisherRideId"));

            Order order = razorpayClient.orders.create(orderRequest);
            return ResponseEntity.ok(order.toString());
        } catch (RazorpayException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating order: " + e.getMessage());
        }
    }

    @PostMapping("/payment")
    public ResponseEntity<?> createPayment(@RequestBody Payment payment) {
        try {
            String generatedSignature = Signature.calculateRFC2104HMAC(payment.getRazorpayOrderId() + "|" + payment.getRazorpayPaymentId(), RAZORPAY_SECRET);
            if (payment.getRazorpaySignature().equals(generatedSignature)) {
                payment.setPaymentDateTime(LocalDateTime.now());
                paymentRepository.save(payment);
                return ResponseEntity.ok("Payment recorded successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid transaction, signature not verified.");
            }
        } catch (SignatureException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error verifying signature: " + e.getMessage());
        }
    }
}
