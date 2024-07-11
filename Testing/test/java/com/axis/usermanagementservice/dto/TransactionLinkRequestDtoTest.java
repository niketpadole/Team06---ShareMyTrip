package com.axis.usermanagementservice.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class TransactionLinkRequestDtoTest {

    @Test
    void getAndSetOrderId() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setOrderId("ORDER123");
        assertEquals("ORDER123", dto.getOrderId());
    }

    @Test
    void getAndSetTimestamp() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        LocalDateTime timestamp = LocalDateTime.now();
        dto.setTimestamp(timestamp);
        assertEquals(timestamp, dto.getTimestamp());
    }

    @Test
    void getAndSetPassengerId() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setPassengerId(1);
        assertEquals(1, dto.getPassengerId());
    }

    @Test
    void getAndSetPassengerRideId() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setPassengerRideId(101);
        assertEquals(101, dto.getPassengerRideId());
    }

    @Test
    void getAndSetPublisherId() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setPublisherId(201);
        assertEquals(201, dto.getPublisherId());
    }

    @Test
    void getAndSetPublisherRideId() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setPublisherRideId(301);
        assertEquals(301, dto.getPublisherRideId());
    }

    @Test
    void getAndSetPublisherName() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setPublisherName("John Doe");
        assertEquals("John Doe", dto.getPublisherName());
    }

    @Test
    void getAndSetPublisherMobile() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setPublisherMobile(9876543210L);
        assertEquals(9876543210L, dto.getPublisherMobile());
    }

    @Test
    void getAndSetFromLocation() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setFromLocation("New York");
        assertEquals("New York", dto.getFromLocation());
    }

    @Test
    void getAndSetToLocation() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setToLocation("Boston");
        assertEquals("Boston", dto.getToLocation());
    }

    @Test
    void getAndSetDateOfJourney() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        Date date = new Date();
        dto.setDateOfJourney(date);
        assertEquals(date, dto.getDateOfJourney());
    }

    @Test
    void getAndSetNoOfSeats() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setNoOfSeats(3);
        assertEquals(3, dto.getNoOfSeats());
    }

    @Test
    void getAndSetDate() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        Date date = new Date();
        dto.setDate(date);
        assertEquals(date, dto.getDate());
    }

    @Test
    void getAndSetDepartureTime() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        LocalTime time = LocalTime.now();
        dto.setDepartureTime(time);
        assertEquals(time, dto.getDepartureTime());
    }

    @Test
    void getAndSetFare() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setFare(50.0f);
        assertEquals(50.0f, dto.getFare());
    }

    @Test
    void getAndSetDistance() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setDistance(300.5f);
        assertEquals(300.5f, dto.getDistance());
    }

    @Test
    void getAndSetJourneyHours() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setJourneyHours(4.5f);
        assertEquals(4.5f, dto.getJourneyHours());
    }

    @Test
    void getAndSetPublisherStatus() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setPublisherStatus("ACTIVE");
        assertEquals("ACTIVE", dto.getPublisherStatus());
    }

    @Test
    void getAndSetPassengerFirstName() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setPassengerFirstName("Jane");
        assertEquals("Jane", dto.getPassengerFirstName());
    }

    @Test
    void getAndSetPassengerLastName() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setPassengerLastName("Doe");
        assertEquals("Doe", dto.getPassengerLastName());
    }

    @Test
    void getAndSetPassengerEmail() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setPassengerEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", dto.getPassengerEmail());
    }

    @Test
    void getAndSetPublisherPaymentStatus() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setPublisherPaymentStatus("PAID");
        assertEquals("PAID", dto.getPublisherPaymentStatus());
    }

    @Test
    void getAndSetPassengerPaymentStatus() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setPassengerPaymentStatus("PENDING");
        assertEquals("PENDING", dto.getPassengerPaymentStatus());
    }

    @Test
    void getAndSetPassengerMobile() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setPassengerMobile("9876543210");
        assertEquals("9876543210", dto.getPassengerMobile());
    }

    @Test
    void getAndSetPassengerCount() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setPassengerCount(2);
        assertEquals(2, dto.getPassengerCount());
    }

    @Test
    void testEqualsAndHashCode() {
        TransactionLinkRequestDto dto1 = new TransactionLinkRequestDto();
        TransactionLinkRequestDto dto2 = new TransactionLinkRequestDto();

        dto1.setOrderId("ORDER123");
        dto2.setOrderId("ORDER123");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setOrderId("ORDER123");
        dto.setTimestamp(LocalDateTime.now());
        dto.setPassengerId(1);
        dto.setPassengerRideId(101);
        dto.setPublisherId(201);
        dto.setPublisherRideId(301);
        dto.setPublisherName("John Doe");
        dto.setPublisherMobile(9876543210L);
        dto.setFromLocation("New York");
        dto.setToLocation("Boston");
        dto.setDateOfJourney(new Date());
        dto.setNoOfSeats(3);
        dto.setDate(new Date());
        dto.setDepartureTime(LocalTime.now());
        dto.setFare(50.0f);
        dto.setDistance(300.5f);
        dto.setJourneyHours(4.5f);
        dto.setPublisherStatus("ACTIVE");
        dto.setPassengerFirstName("Jane");
        dto.setPassengerLastName("Doe");
        dto.setPassengerEmail("jane.doe@example.com");
        dto.setPublisherPaymentStatus("PAID");
        dto.setPassengerPaymentStatus("PENDING");
        dto.setPassengerMobile("9876543210");
        dto.setPassengerCount(2);

        String expectedString = "TransactionLinkRequestDto(orderId=ORDER123, timestamp=" + dto.getTimestamp() + ", passengerId=1, passengerRideId=101, publisherId=201, publisherRideId=301, publisherName=John Doe, publisherMobile=9876543210, fromLocation=New York, toLocation=Boston, dateOfJourney=" + dto.getDateOfJourney() + ", noOfSeats=3, date=" + dto.getDate() + ", departureTime=" + dto.getDepartureTime() + ", fare=50.0, distance=300.5, journeyHours=4.5, publisherStatus=ACTIVE, passengerFirstName=Jane, passengerLastName=Doe, passengerEmail=jane.doe@example.com, publisherPaymentStatus=PAID, passengerPaymentStatus=PENDING, passengerMobile=9876543210, passengerCount=2)";
        assertEquals(expectedString, dto.toString());
    }
}
