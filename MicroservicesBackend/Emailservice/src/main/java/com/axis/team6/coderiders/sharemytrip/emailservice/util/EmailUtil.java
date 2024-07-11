package com.axis.team6.coderiders.sharemytrip.emailservice.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {


  @Autowired
    private JavaMailSender mailSender;

    public void sendOtp(String email, String otp) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("Share My Trip Team");
        helper.setText("Your OTP code for reset password  is: " + otp);

        mailSender.send(message);
    }
    public void sendRideConfirmation(String email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("Ride confirmation");
        String content = "<p>Dear Publisher,</p>"
                + "<p>Your ride is published successfully.</p>"
                + "<p><img src='https://share-my-trip.s3.ap-south-1.amazonaws.com/sharemytrip+Logo+(1).png' alt='Ride Image'></p>"
                + "<p>Happy Journey!</p>"
                + "<p>Thanks for using Share My Trip.</p>";
        helper.setText(content, true);

        mailSender.send(message);
    }
    public void sendRideBookedConfirmation(String email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("Ride booked confirmation");
        String content = "<p>Dear Passenger,</p>"
                + "<p>Your ride has been booked successfully.</p>"
                + "<p><img src='https://share-my-trip.s3.ap-south-1.amazonaws.com/sharemytrip+Logo+(1).png' alt='Ride Image'></p>"
                + "<p>Happy Journey!</p>"
                + "<p>Thanks for using Share My Trip.</p>";
        helper.setText(content, true);

        mailSender.send(message);
    }

    public void sendRideCanceledNotification(String email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("Ride canceled notification");
        String content = "<p>Dear Passenger,</p>"
                + "<p>Your ride has been canceled successfully.</p>"
                + "<p><img src='https://share-my-trip.s3.ap-south-1.amazonaws.com/sharemytrip+Logo+(1).png' alt='Ride Image'></p>"
                + "<p>We apologize for any inconvenience.</p>"
                + "<p>Thanks for using Share My Trip.</p>";
        helper.setText(content, true);

        mailSender.send(message);
    }
    public void sendPublisherRideCanceledNotification(String email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("Ride canceled notification");
        String content = "<p>Dear Publisher,</p>"
                + "<p>Your Published ride has been canceled successfully.</p>"
                + "<p><img src='https://share-my-trip.s3.ap-south-1.amazonaws.com/sharemytrip+Logo+(1).png' alt='Ride Image'></p>"
                + "<p>We apologize for any inconvenience.</p>"
                + "<p>Thanks for using Share My Trip.</p>";
        helper.setText(content, true);

        mailSender.send(message);
    }


    public void sendPublisherRideCanceledNotificationToPassenger(String email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("Ride canceled notification");
        String content = "<p>Dear Passenger,</p>"
                + "<p>Your ride has been canceled by publisher.</p>"
                + "<p><img src='https://share-my-trip.s3.ap-south-1.amazonaws.com/sharemytrip+Logo+(1).png' alt='Ride Image'></p>"
                + "<p>We apologize for any inconvenience.</p>"
                + "<p>Thanks for using Share My Trip.</p>";
        helper.setText(content, true);

        mailSender.send(message);
    }

    public void sendPassengerRideCanceledNotificationToPublisher(String email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("Ride canceled notification");
        String content =  "<p>Dear Publisher,</p>"
                + "<p>We regret to inform you that a passenger has canceled their seat for your ride.</p>"
                + "<p>Rest assured, your ride will be listed for other passengers to book.</p>"
                + "<p><img src='https://share-my-trip.s3.ap-south-1.amazonaws.com/sharemytrip+Logo+(1).png' alt='Ride Image'></p>"
                + "<p>We apologize for any inconvenience.</p>"
                + "<p>Thanks for using Share My Trip.</p>";
        helper.setText(content, true);

        mailSender.send(message);
    }
}
