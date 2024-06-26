package com.axis.team6.coderiders.sharemytrip.emailservice.controller;

import com.axis.team6.coderiders.sharemytrip.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/email")
public class EmailController {

@Autowired
private EmailService emailService;

    @PostMapping("/send-otp/{email}")
    public ResponseEntity<String> sendOtp(@PathVariable String email) {
        return ResponseEntity.ok(emailService.otpVerification(email));
    }

    @PostMapping("/verify-otp/{email}/{otp}")
    public ResponseEntity<String> verifyOtp(@PathVariable String email, @PathVariable String otp) {
        return ResponseEntity.ok(emailService.verifyOtp(email, otp));
    }
}
