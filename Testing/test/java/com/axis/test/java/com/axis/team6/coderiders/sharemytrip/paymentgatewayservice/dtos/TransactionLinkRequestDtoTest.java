package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TransactionLinkRequestDtoTest {

    private TransactionLinkRequestDto dto;

    @BeforeEach
    void setUp() {
        dto = new TransactionLinkRequestDto();
    }

    @Test
    void getAndSetOrderId() {
        dto.setOrderId("order123");
        assertEquals("order123", dto.getOrderId());
    }

//    @Test
//    void getAndSetTimestamp() {
//        Date timestamp = new Date();
//        dto.setTimestamp(timestamp);
//        assertEquals(timestamp, dto.getTimestamp());
//    }

    @Test
    void getAndSetPassengerId() {
        dto.setPassengerId(1);
        assertEquals(1, dto.getPassengerId());
    }

    @Test
    void getAndSetPassengerRideId() {
        dto.setPassengerRideId(2);
        assertEquals(2, dto.getPassengerRideId());
    }

    @Test
    void getAndSetPublisherId() {
        dto.setPublisherId(3);
        assertEquals(3, dto.getPublisherId());
    }

    @Test
    void getAndSetPublisherRideId() {
        dto.setPublisherRideId(4);
        assertEquals(4, dto.getPublisherRideId());
    }

    @Test
    void getAndSetPublisherName() {
        dto.setPublisherName("Jane Doe");
        assertEquals("Jane Doe", dto.getPublisherName());
    }

    @Test
    void getAndSetPublisherMobile() {
        dto.setPublisherMobile(9876543210L);
        assertEquals(9876543210L, dto.getPublisherMobile());
    }

    @Test
    void getAndSetFromLocation() {
        dto.setFromLocation("Boston");
        assertEquals("Boston", dto.getFromLocation());
    }

    @Test
    void getAndSetToLocation() {
        dto.setToLocation("Chicago");
        assertEquals("Chicago", dto.getToLocation());
    }

    @Test
    void getAndSetDateOfJourney() {
        Date dateOfJourney = new Date();
        dto.setDateOfJourney(dateOfJourney);
        assertEquals(dateOfJourney, dto.getDateOfJourney());
    }

    @Test
    void getAndSetNoOfSeats() {
        dto.setNoOfSeats(5);
        assertEquals(5, dto.getNoOfSeats());
    }

    @Test
    void getAndSetDate() {
        Date date = new Date();
        dto.setDate(date);
        assertEquals(date, dto.getDate());
    }

//    @Test
//    void getAndSetDepartureTime() {
//        Time departureTime = new Time(System.currentTimeMillis());
//        dto.setDepartureTime(departureTime);
//        assertEquals(departureTime, dto.getDepartureTime());
//    }

    @Test
    void getAndSetFare() {
        dto.setFare(150.75f);
        assertEquals(150.75f, dto.getFare());
    }

    @Test
    void getAndSetDistance() {
        dto.setDistance(300.5f);
        assertEquals(300.5f, dto.getDistance());
    }

    @Test
    void getAndSetJourneyHours() {
        dto.setJourneyHours(5.5f);
        assertEquals(5.5f, dto.getJourneyHours());
    }

    @Test
    void getAndSetPublisherStatus() {
        dto.setPublisherStatus("Active");
        assertEquals("Active", dto.getPublisherStatus());
    }

    @Test
    void getAndSetPublisherPaymentStatus() {
        dto.setPublisherPaymentStatus("Paid");
        assertEquals("Paid", dto.getPublisherPaymentStatus());
    }

    @Test
    void getAndSetPassengerPaymentStatus() {
        dto.setPassengerPaymentStatus("Unpaid");
        assertEquals("Unpaid", dto.getPassengerPaymentStatus());
    }

    @Test
    void getAndSetPassengerFirstName() {
        dto.setPassengerFirstName("John");
        assertEquals("John", dto.getPassengerFirstName());
    }

    @Test
    void getAndSetPassengerLastName() {
        dto.setPassengerLastName("Smith");
        assertEquals("Smith", dto.getPassengerLastName());
    }

    @Test
    void getAndSetPassengerEmail() {
        dto.setPassengerEmail("john.smith@example.com");
        assertEquals("john.smith@example.com", dto.getPassengerEmail());
    }

    @Test
    void getAndSetPassengerMobile() {
        dto.setPassengerMobile("1234567890");
        assertEquals("1234567890", dto.getPassengerMobile());
    }

    @Test
    void getAndSetPassengerCount() {
        dto.setPassengerCount(3);
        assertEquals(3, dto.getPassengerCount());
    }

    @Test
    void testEquals() {
        TransactionLinkRequestDto dto1 = new TransactionLinkRequestDto();
        TransactionLinkRequestDto dto2 = new TransactionLinkRequestDto();
        assertEquals(dto1, dto2);

        dto1.setOrderId("order123");
        assertNotEquals(dto1, dto2);

        dto2.setOrderId("order123");
        assertEquals(dto1, dto2);
    }

    @Test
    void canEqual() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        assertTrue(dto.canEqual(new TransactionLinkRequestDto()));
        assertFalse(dto.canEqual(new Object()));
    }

    @Test
    void testHashCode() {
        TransactionLinkRequestDto dto1 = new TransactionLinkRequestDto();
        TransactionLinkRequestDto dto2 = new TransactionLinkRequestDto();
        assertEquals(dto1.hashCode(), dto2.hashCode());

        dto1.setOrderId("order123");
        assertNotEquals(dto1.hashCode(), dto2.hashCode());

        dto2.setOrderId("order123");
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        dto.setOrderId("order123");
        assertNotNull(dto.toString());
        assertTrue(dto.toString().contains("orderId=order123"));
    }
}
