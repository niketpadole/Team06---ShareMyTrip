package com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.sql.Time;
import static org.junit.jupiter.api.Assertions.*;

class RideDetailsDTOTest {

    @Test
    void getAndSetPassengerId() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setPassengerId(1);
        assertEquals(1, dto.getPassengerId());
    }

    @Test
    void getAndSetPublisherName() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setPublisherName("John Doe");
        assertEquals("John Doe", dto.getPublisherName());
    }

    @Test
    void getAndSetPublisherMobile() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setPublisherMobile(9876543210L);
        assertEquals(9876543210L, dto.getPublisherMobile());
    }

    @Test
    void getAndSetFromLocation() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setFromLocation("New York");
        assertEquals("New York", dto.getFromLocation());
    }

    @Test
    void getAndSetToLocation() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setToLocation("Boston");
        assertEquals("Boston", dto.getToLocation());
    }

    @Test
    void getAndSetNoOfSeats() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setNoOfSeats(3);
        assertEquals(3, dto.getNoOfSeats());
    }

    @Test
    void getAndSetDate() {
        RideDetailsDTO dto = new RideDetailsDTO();
        Date date = Date.valueOf("2023-06-22");
        dto.setDate(date);
        assertEquals(date, dto.getDate());
    }

    @Test
    void getAndSetDepartureTime() {
        RideDetailsDTO dto = new RideDetailsDTO();
        Time time = Time.valueOf("10:00:00");
        dto.setDepartureTime(time);
        assertEquals(time, dto.getDepartureTime());
    }

    @Test
    void getAndSetFare() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setFare(50.0f);
        assertEquals(50.0f, dto.getFare());
    }

    @Test
    void getAndSetDistance() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setDistance(300.5f);
        assertEquals(300.5f, dto.getDistance());
    }

    @Test
    void getAndSetJourneyHours() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setJourneyHours(4.5f);
        assertEquals(4.5f, dto.getJourneyHours());
    }

    @Test
    void getAndSetPublisherStatus() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setPublisherStatus("ONGOING");
        assertEquals("ONGOING", dto.getPublisherStatus());
    }

    @Test
    void getAndSetPublisherRideId() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setPublisherRideId(1);
        assertEquals(1, dto.getPublisherRideId());
    }

    @Test
    void getAndSetPassengerRideId() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setPassengerRideId(2);
        assertEquals(2, dto.getPassengerRideId());
    }

    @Test
    void getAndSetPassengerCount() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setPassengerCount(3);
        assertEquals(3, dto.getPassengerCount());
    }

    @Test
    void getAndSetPublisherPaymentStatus() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setPublisherPaymentStatus("PAID");
        assertEquals("PAID", dto.getPublisherPaymentStatus());
    }

    @Test
    void getAndSetPassengerPaymentStatus() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setPassengerPaymentStatus("PENDING");
        assertEquals("PENDING", dto.getPassengerPaymentStatus());
    }

    @Test
    void getAndSetPassengerMobile() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setPassengerMobile("9876543210");
        assertEquals("9876543210", dto.getPassengerMobile());
    }

    @Test
    void testEqualsAndHashCode() {
        RideDetailsDTO dto1 = new RideDetailsDTO();
        RideDetailsDTO dto2 = new RideDetailsDTO();

        dto1.setPassengerId(1);
        dto2.setPassengerId(1);

        dto1.setPublisherName("John Doe");
        dto2.setPublisherName("John Doe");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        RideDetailsDTO dto = new RideDetailsDTO();
        dto.setPassengerId(1);
        dto.setPublisherName("John Doe");
        dto.setPublisherMobile(9876543210L);
        dto.setFromLocation("New York");
        dto.setToLocation("Boston");
        dto.setNoOfSeats(3);
        dto.setDate(Date.valueOf("2023-06-22"));
        dto.setDepartureTime(Time.valueOf("10:00:00"));
        dto.setFare(50.0f);
        dto.setDistance(300.5f);
        dto.setJourneyHours(4.5f);
        dto.setPublisherStatus("ONGOING");
        dto.setPublisherRideId(1);
        dto.setPassengerRideId(2);
        dto.setPassengerCount(3);
        dto.setPublisherPaymentStatus("PAID");
        dto.setPassengerPaymentStatus("PENDING");
        dto.setPassengerMobile("9876543210");

        String expectedString = "RideDetailsDTO(passengerId=1, publisherName=John Doe, publisherMobile=9876543210, fromLocation=New York, toLocation=Boston, noOfSeats=3, date=2023-06-22, departureTime=10:00:00, fare=50.0, distance=300.5, journeyHours=4.5, publisherStatus=ONGOING, publisherRideId=1, passengerRideId=2, passengerCount=3, publisherPaymentStatus=PAID, passengerPaymentStatus=PENDING, passengerMobile=9876543210)";
        assertEquals(expectedString, dto.toString());
    }
}
