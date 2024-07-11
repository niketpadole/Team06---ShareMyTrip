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

    @PostMapping("/send-publisher-confirmation")
    public void sendPublisherConfirmation(@RequestParam String publisherEmail) {
        emailService.sendRideConfirmationEmail(publisherEmail);
    }
    @PostMapping("/send-passenger-booked-confirmation")
    public void sendPassengerBookedConfirmation(@RequestParam String passengerEmail) {
        emailService.sendRideBookedEmail(passengerEmail);
    }

    @PostMapping("/send-passenger-canceled-notification")
    public void sendPassengerCanceledNotification(@RequestParam String passengerEmail) {
        emailService.sendPassengerRideCanceledEmail(passengerEmail);
    }
    @PostMapping("/send-publisher-canceled-notification")
    public void sendPublisherCanceledConfirmation(@RequestParam String publisherEmail) {
        emailService.sendPublisherRideCanceledEmail(publisherEmail);
    }
    @PostMapping("/send-publisher-canceled-notification-passenger")
    public void sendPublisherRideCanceledConfirmationToPassenger(@RequestParam String passengerEmail) {
        emailService.sendPublisherRideCanceledConfirmationToPassenger(passengerEmail);
    }
    @PostMapping("/send-pasenger-canceled-notification-publisher")
    public void sendPassengerRideCanceledEmailToPublisher(@RequestParam String publisherEmail) {
        emailService.sendPassengerRideCanceledConfirmationToPublisher(publisherEmail);
    }
}
