package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDetailsTest {

    @Test
    void getOrderId() {
        TransactionDetails details = new TransactionDetails();
        String orderId = "12345";
        details.setOrderId(orderId);
        assertEquals(orderId, details.getOrderId());
    }

    @Test
    void getPaymentId() {
        TransactionDetails details = new TransactionDetails();
        String paymentId = "98765";
        details.setPaymentId(paymentId);
        assertEquals(paymentId, details.getPaymentId());
    }

    @Test
    void getPaymentLink() {
        TransactionDetails details = new TransactionDetails();
        String paymentLink = "http://payment.link";
        details.setPaymentLink(paymentLink);
        assertEquals(paymentLink, details.getPaymentLink());
    }

    @Test
    void getStatus() {
        TransactionDetails details = new TransactionDetails();
        PaymentStatus status = PaymentStatus.SUCCESS;
        details.setStatus(status);
        assertEquals(status, details.getStatus());
    }

    @Test
    void getPassengerId() {
        TransactionDetails details = new TransactionDetails();
        Integer passengerId = 123;
        details.setPassengerId(passengerId);
        assertEquals(passengerId, details.getPassengerId());
    }

    @Test
    void getDateOfJourney() {
        TransactionDetails details = new TransactionDetails();
        Date date = new Date();
        details.setDateOfJourney(date);
        assertEquals(date, details.getDateOfJourney());
    }

    @Test
    void getTotalFare() {
        TransactionDetails details = new TransactionDetails();
        Double fare = 50.0;
        details.setTotalFare(fare);
        assertEquals(fare, details.getTotalFare());
    }

    @Test
    void getPublisherRideId() {
        TransactionDetails details = new TransactionDetails();
        Integer rideId = 456;
        details.setPublisherRideId(rideId);
        assertEquals(rideId, details.getPublisherRideId());
    }

    @Test
    void getPassengerRideId() {
        TransactionDetails details = new TransactionDetails();
        Integer rideId = 789;
        details.setPassengerRideId(rideId);
        assertEquals(rideId, details.getPassengerRideId());
    }

    @Test
    void getPublisherId() {
        TransactionDetails details = new TransactionDetails();
        Integer publisherId = 321;
        details.setPublisherId(publisherId);
        assertEquals(publisherId, details.getPublisherId());
    }

    @Test
    void getDepartureTime() {
        TransactionDetails details = new TransactionDetails();
        LocalTime time = LocalTime.now();
        details.setDepartureTime(time);
        assertEquals(time, details.getDepartureTime());
    }

    @Test
    void getReservedSeats() {
        TransactionDetails details = new TransactionDetails();
        Integer seats = 4;
        details.setReservedSeats(seats);
        assertEquals(seats, details.getReservedSeats());
    }

    @Test
    void getPassengerFirstName() {
        TransactionDetails details = new TransactionDetails();
        String firstName = "John";
        details.setPassengerFirstName(firstName);
        assertEquals(firstName, details.getPassengerFirstName());
    }

    @Test
    void getPassengerLastName() {
        TransactionDetails details = new TransactionDetails();
        String lastName = "Doe";
        details.setPassengerLastName(lastName);
        assertEquals(lastName, details.getPassengerLastName());
    }

    @Test
    void getTimestamp() {
        TransactionDetails details = new TransactionDetails();
        LocalDateTime timestamp = LocalDateTime.now();
        details.setTimestamp(timestamp);
        assertEquals(timestamp, details.getTimestamp());
    }

    @Test
    void setOrderId() {
        TransactionDetails details = new TransactionDetails();
        String orderId = "54321";
        details.setOrderId(orderId);
        assertEquals(orderId, details.getOrderId());
    }

    @Test
    void setPaymentId() {
        TransactionDetails details = new TransactionDetails();
        String paymentId = "56789";
        details.setPaymentId(paymentId);
        assertEquals(paymentId, details.getPaymentId());
    }

    @Test
    void setPaymentLink() {
        TransactionDetails details = new TransactionDetails();
        String paymentLink = "http://newpayment.link";
        details.setPaymentLink(paymentLink);
        assertEquals(paymentLink, details.getPaymentLink());
    }

    @Test
    void setStatus() {
        TransactionDetails details = new TransactionDetails();
        PaymentStatus status = PaymentStatus.FAILURE;
        details.setStatus(status);
        assertEquals(status, details.getStatus());
    }

    @Test
    void setPassengerId() {
        TransactionDetails details = new TransactionDetails();
        Integer passengerId = 321;
        details.setPassengerId(passengerId);
        assertEquals(passengerId, details.getPassengerId());
    }

    @Test
    void setDateOfJourney() {
        TransactionDetails details = new TransactionDetails();
        Date date = new Date();
        details.setDateOfJourney(date);
        assertEquals(date, details.getDateOfJourney());
    }

    @Test
    void setTotalFare() {
        TransactionDetails details = new TransactionDetails();
        Double fare = 75.0;
        details.setTotalFare(fare);
        assertEquals(fare, details.getTotalFare());
    }

    @Test
    void setPublisherRideId() {
        TransactionDetails details = new TransactionDetails();
        Integer rideId = 654;
        details.setPublisherRideId(rideId);
        assertEquals(rideId, details.getPublisherRideId());
    }

    @Test
    void setPassengerRideId() {
        TransactionDetails details = new TransactionDetails();
        Integer rideId = 987;
        details.setPassengerRideId(rideId);
        assertEquals(rideId, details.getPassengerRideId());
    }

    @Test
    void setPublisherId() {
        TransactionDetails details = new TransactionDetails();
        Integer publisherId = 987;
        details.setPublisherId(publisherId);
        assertEquals(publisherId, details.getPublisherId());
    }

    @Test
    void setDepartureTime() {
        TransactionDetails details = new TransactionDetails();
        LocalTime time = LocalTime.now();
        details.setDepartureTime(time);
        assertEquals(time, details.getDepartureTime());
    }

    @Test
    void setReservedSeats() {
        TransactionDetails details = new TransactionDetails();
        Integer seats = 5;
        details.setReservedSeats(seats);
        assertEquals(seats, details.getReservedSeats());
    }

    @Test
    void setPassengerFirstName() {
        TransactionDetails details = new TransactionDetails();
        String firstName = "Jane";
        details.setPassengerFirstName(firstName);
        assertEquals(firstName, details.getPassengerFirstName());
    }

    @Test
    void setPassengerLastName() {
        TransactionDetails details = new TransactionDetails();
        String lastName = "Smith";
        details.setPassengerLastName(lastName);
        assertEquals(lastName, details.getPassengerLastName());
    }

    @Test
    void setTimestamp() {
        TransactionDetails details = new TransactionDetails();
        LocalDateTime timestamp = LocalDateTime.now();
        details.setTimestamp(timestamp);
        assertEquals(timestamp, details.getTimestamp());
    }

    @Test
    void testEquals() {
        TransactionDetails details1 = new TransactionDetails();
        TransactionDetails details2 = new TransactionDetails();
        assertEquals(details1, details2);

        details1.setOrderId("12345");
        details2.setOrderId("12345");
        assertEquals(details1, details2);

        details2.setOrderId("54321");
        assertNotEquals(details1, details2);
    }

    @Test
    void canEqual() {
        TransactionDetails details1 = new TransactionDetails();
        assertTrue(details1.canEqual(new TransactionDetails()));
    }

    @Test
    void testHashCode() {
        TransactionDetails details = new TransactionDetails();
        assertNotNull(details.hashCode());
    }

    @Test
    void testToString() {
        TransactionDetails details = new TransactionDetails();
        assertNotNull(details.toString());
    }
}
