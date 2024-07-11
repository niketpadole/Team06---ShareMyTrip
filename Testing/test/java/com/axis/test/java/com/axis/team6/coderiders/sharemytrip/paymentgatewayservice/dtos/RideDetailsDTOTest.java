package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class RideDetailsDTOTest {

    private RideDetailsDTO rideDetailsDTO;

    @BeforeEach
    void setUp() {
        rideDetailsDTO = new RideDetailsDTO();
    }

    @Test
    void getAndSetPassengerId() {
        rideDetailsDTO.setPassengerId(1);
        assertEquals(1, rideDetailsDTO.getPassengerId());
    }

    @Test
    void getAndSetPublisherName() {
        rideDetailsDTO.setPublisherName("John Doe");
        assertEquals("John Doe", rideDetailsDTO.getPublisherName());
    }

    @Test
    void getAndSetPublisherMobile() {
        rideDetailsDTO.setPublisherMobile(1234567890L);
        assertEquals(1234567890L, rideDetailsDTO.getPublisherMobile());
    }

    @Test
    void getAndSetFromLocation() {
        rideDetailsDTO.setFromLocation("New York");
        assertEquals("New York", rideDetailsDTO.getFromLocation());
    }

    @Test
    void getAndSetToLocation() {
        rideDetailsDTO.setToLocation("Boston");
        assertEquals("Boston", rideDetailsDTO.getToLocation());
    }

    @Test
    void getAndSetNoOfSeats() {
        rideDetailsDTO.setNoOfSeats(4);
        assertEquals(4, rideDetailsDTO.getNoOfSeats());
    }

    @Test
    void getAndSetDate() {
        Date date = new Date();
        rideDetailsDTO.setDate(date);
        assertEquals(date, rideDetailsDTO.getDate());
    }

//    @Test
//    void getAndSetDepartureTime() {
//        Time time = new Time(System.currentTimeMillis());
//        rideDetailsDTO.setDepartureTime(time);
//        assertEquals(time, rideDetailsDTO.getDepartureTime());
//    }

    @Test
    void getAndSetFare() {
        rideDetailsDTO.setFare(100.50f);
        assertEquals(100.50f, rideDetailsDTO.getFare());
    }

    @Test
    void getAndSetDistance() {
        rideDetailsDTO.setDistance(200.5f);
        assertEquals(200.5f, rideDetailsDTO.getDistance());
    }

    @Test
    void getAndSetJourneyHours() {
        rideDetailsDTO.setJourneyHours(4.5f);
        assertEquals(4.5f, rideDetailsDTO.getJourneyHours());
    }

    @Test
    void getAndSetPublisherStatus() {
        rideDetailsDTO.setPublisherStatus("Active");
        assertEquals("Active", rideDetailsDTO.getPublisherStatus());
    }

    @Test
    void getAndSetPublisherRideId() {
        rideDetailsDTO.setPublisherRideId(10);
        assertEquals(10, rideDetailsDTO.getPublisherRideId());
    }

    @Test
    void getAndSetPassengerCount() {
        rideDetailsDTO.setPassengerCount(3);
        assertEquals(3, rideDetailsDTO.getPassengerCount());
    }

    @Test
    void getAndSetPublisherPaymentStatus() {
        rideDetailsDTO.setPublisherPaymentStatus("Paid");
        assertEquals("Paid", rideDetailsDTO.getPublisherPaymentStatus());
    }

    @Test
    void getAndSetPassengerPaymentStatus() {
        rideDetailsDTO.setPassengerPaymentStatus("Unpaid");
        assertEquals("Unpaid", rideDetailsDTO.getPassengerPaymentStatus());
    }

    @Test
    void getAndSetPassengerMobile() {
        rideDetailsDTO.setPassengerMobile("9876543210");
        assertEquals("9876543210", rideDetailsDTO.getPassengerMobile());
    }

    @Test
    void testEquals() {
        RideDetailsDTO dto1 = new RideDetailsDTO();
        RideDetailsDTO dto2 = new RideDetailsDTO();
        assertEquals(dto1, dto2);

        dto1.setPassengerId(1);
        assertNotEquals(dto1, dto2);

        dto2.setPassengerId(1);
        assertEquals(dto1, dto2);
    }

    @Test
    void canEqual() {
        RideDetailsDTO dto = new RideDetailsDTO();
        assertTrue(dto.canEqual(new RideDetailsDTO()));
        assertFalse(dto.canEqual(new Object()));
    }

    @Test
    void testHashCode() {
        RideDetailsDTO dto1 = new RideDetailsDTO();
        RideDetailsDTO dto2 = new RideDetailsDTO();
        assertEquals(dto1.hashCode(), dto2.hashCode());

        dto1.setPassengerId(1);
        assertNotEquals(dto1.hashCode(), dto2.hashCode());

        dto2.setPassengerId(1);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setPassengerId(1);
        assertNotNull(dto.toString());
        assertTrue(dto.toString().contains("passengerId=1"));
    }
}
