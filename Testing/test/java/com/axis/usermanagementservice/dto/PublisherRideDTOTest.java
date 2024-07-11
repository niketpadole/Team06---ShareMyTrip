package com.axis.usermanagementservice.dto;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;

class PublisherRideDTOTest {

    @Test
    void getAndSetPublisherRideId() {
        PublisherRideDTO dto = new PublisherRideDTO();
        dto.setPublisherRideId(1);
        assertEquals(1, dto.getPublisherRideId());
    }

    @Test
    void getAndSetPublisherId() {
        PublisherRideDTO dto = new PublisherRideDTO();
        dto.setPublisherId(101);
        assertEquals(101, dto.getPublisherId());
    }

    @Test
    void getAndSetFromLocation() {
        PublisherRideDTO dto = new PublisherRideDTO();
        dto.setFromLocation("New York");
        assertEquals("New York", dto.getFromLocation());
    }

    @Test
    void getAndSetToLocation() {
        PublisherRideDTO dto = new PublisherRideDTO();
        dto.setToLocation("Boston");
        assertEquals("Boston", dto.getToLocation());
    }

    @Test
    void getAndSetDistance() {
        PublisherRideDTO dto = new PublisherRideDTO();
        dto.setDistance(300.5f);
        assertEquals(300.5f, dto.getDistance());
    }

    @Test
    void getAndSetJourneyHours() {
        PublisherRideDTO dto = new PublisherRideDTO();
        dto.setJourneyHours(4.5f);
        assertEquals(4.5f, dto.getJourneyHours());
    }

    @Test
    void getAndSetAvailableSeats() {
        PublisherRideDTO dto = new PublisherRideDTO();
        dto.setAvailableSeats(3);
        assertEquals(3, dto.getAvailableSeats());
    }

    @Test
    void getAndSetReservedSeats() {
        PublisherRideDTO dto = new PublisherRideDTO();
        dto.setReservedSeats(1);
        assertEquals(1, dto.getReservedSeats());
    }

    @Test
    void getAndSetDateOfJourney() {
        PublisherRideDTO dto = new PublisherRideDTO();
        Date date = Date.valueOf("2023-06-22");
        dto.setDateOfJourney(date);
        assertEquals(date, dto.getDateOfJourney());
    }

    @Test
    void getAndSetTimeOfJourney() {
        PublisherRideDTO dto = new PublisherRideDTO();
        Time time = Time.valueOf("10:00:00");
        dto.setTimeOfJourney(time);
        assertEquals(time, dto.getTimeOfJourney());
    }

    @Test
    void getAndSetFarePerSeat() {
        PublisherRideDTO dto = new PublisherRideDTO();
        dto.setFarePerSeat(50.0f);
        assertEquals(50.0f, dto.getFarePerSeat());
    }

    @Test
    void getAndSetAboutRide() {
        PublisherRideDTO dto = new PublisherRideDTO();
        dto.setAboutRide("Comfortable ride with AC");
        assertEquals("Comfortable ride with AC", dto.getAboutRide());
    }

    @Test
    void getAndSetTimestamp() {
        PublisherRideDTO dto = new PublisherRideDTO();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        dto.setTimestamp(timestamp);
        assertEquals(timestamp, dto.getTimestamp());
    }

    @Test
    void getAndSetStatus() {
        PublisherRideDTO dto = new PublisherRideDTO();
        dto.setStatus("COMPLETED");
        assertEquals("COMPLETED", dto.getStatus());
    }

    @Test
    void getAndSetPaymentStatus() {
        PublisherRideDTO dto = new PublisherRideDTO();
        dto.setPaymentStatus("PAID");
        assertEquals("PAID", dto.getPaymentStatus());
    }

    @Test
    void testEqualsAndHashCode() {
        PublisherRideDTO dto1 = new PublisherRideDTO();
        PublisherRideDTO dto2 = new PublisherRideDTO();

        dto1.setPublisherRideId(1);
        dto2.setPublisherRideId(1);

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        PublisherRideDTO dto = new PublisherRideDTO();
        dto.setPublisherRideId(1);
        dto.setPublisherId(101);
        dto.setFromLocation("New York");
        dto.setToLocation("Boston");
        dto.setDistance(300.5f);
        dto.setJourneyHours(4.5f);
        dto.setAvailableSeats(3);
        dto.setReservedSeats(1);
        dto.setDateOfJourney(Date.valueOf("2023-06-22"));
        dto.setTimeOfJourney(Time.valueOf("10:00:00"));
        dto.setFarePerSeat(50.0f);
        dto.setAboutRide("Comfortable ride with AC");
        dto.setTimestamp(new Timestamp(System.currentTimeMillis()));
        dto.setStatus("COMPLETED");
        dto.setPaymentStatus("PAID");

        String expectedString = "PublisherRideDTO(publisherRideId=1, publisherId=101, fromLocation=New York, toLocation=Boston, distance=300.5, journeyHours=4.5, availableSeats=3, reservedSeats=1, dateOfJourney=2023-06-22, timeOfJourney=10:00:00, farePerSeat=50.0, aboutRide=Comfortable ride with AC, timestamp=" + dto.getTimestamp() + ", status=COMPLETED, paymentStatus=PAID)";
        assertEquals(expectedString, dto.toString());
    }
}
