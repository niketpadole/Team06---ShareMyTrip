package com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class RideDetailsViewTest {

    @Test
    void getAndSetRideDetailsKey() {
        RideDetailsView entity = new RideDetailsView();
        entity.setRideDetailsKey("key123");
        assertEquals("key123", entity.getRideDetailsKey());
    }

    @Test
    void getAndSetPassengerId() {
        RideDetailsView entity = new RideDetailsView();
        entity.setPassengerId(1);
        assertEquals(1, entity.getPassengerId());
    }

    @Test
    void getAndSetPublisherId() {
        RideDetailsView entity = new RideDetailsView();
        entity.setPublisherId(2);
        assertEquals(2, entity.getPublisherId());
    }

    @Test
    void getAndSetPublisherName() {
        RideDetailsView entity = new RideDetailsView();
        entity.setPublisherName("John Doe");
        assertEquals("John Doe", entity.getPublisherName());
    }

    @Test
    void getAndSetPublisherMobile() {
        RideDetailsView entity = new RideDetailsView();
        entity.setPublisherMobile(9876543210L);
        assertEquals(9876543210L, entity.getPublisherMobile());
    }

    @Test
    void getAndSetFromLocation() {
        RideDetailsView entity = new RideDetailsView();
        entity.setFromLocation("New York");
        assertEquals("New York", entity.getFromLocation());
    }

    @Test
    void getAndSetToLocation() {
        RideDetailsView entity = new RideDetailsView();
        entity.setToLocation("Boston");
        assertEquals("Boston", entity.getToLocation());
    }

    @Test
    void getAndSetNoOfSeats() {
        RideDetailsView entity = new RideDetailsView();
        entity.setNoOfSeats(3);
        assertEquals(3, entity.getNoOfSeats());
    }

    @Test
    void getAndSetDate() {
        RideDetailsView entity = new RideDetailsView();
        Date date = Date.valueOf("2023-06-22");
        entity.setDate(date);
        assertEquals(date, entity.getDate());
    }

    @Test
    void getAndSetDepartureTime() {
        RideDetailsView entity = new RideDetailsView();
        Time time = Time.valueOf("10:00:00");
        entity.setDepartureTime(time);
        assertEquals(time, entity.getDepartureTime());
    }

    @Test
    void getAndSetFare() {
        RideDetailsView entity = new RideDetailsView();
        entity.setFare(50.0f);
        assertEquals(50.0f, entity.getFare());
    }

    @Test
    void getAndSetDistance() {
        RideDetailsView entity = new RideDetailsView();
        entity.setDistance(300.5f);
        assertEquals(300.5f, entity.getDistance());
    }

    @Test
    void getAndSetJourneyHours() {
        RideDetailsView entity = new RideDetailsView();
        entity.setJourneyHours(4.5f);
        assertEquals(4.5f, entity.getJourneyHours());
    }

    @Test
    void getAndSetPublisherStatus() {
        RideDetailsView entity = new RideDetailsView();
        entity.setPublisherStatus("ONGOING");
        assertEquals("ONGOING", entity.getPublisherStatus());
    }

    @Test
    void getAndSetPublisherRideId() {
        RideDetailsView entity = new RideDetailsView();
        entity.setPublisherRideId(5);
        assertEquals(5, entity.getPublisherRideId());
    }

    @Test
    void getAndSetPassengerRideId() {
        RideDetailsView entity = new RideDetailsView();
        entity.setPassengerRideId(6);
        assertEquals(6, entity.getPassengerRideId());
    }

    @Test
    void getAndSetPassengerCount() {
        RideDetailsView entity = new RideDetailsView();
        entity.setPassengerCount(3);
        assertEquals(3, entity.getPassengerCount());
    }

    @Test
    void getAndSetPublisherPaymentStatus() {
        RideDetailsView entity = new RideDetailsView();
        entity.setPublisherPaymentStatus("PAID");
        assertEquals("PAID", entity.getPublisherPaymentStatus());
    }

    @Test
    void getAndSetPassengerPaymentStatus() {
        RideDetailsView entity = new RideDetailsView();
        entity.setPassengerPaymentStatus("PENDING");
        assertEquals("PENDING", entity.getPassengerPaymentStatus());
    }

    @Test
    void getAndSetPassengerMobile() {
        RideDetailsView entity = new RideDetailsView();
        entity.setPassengerMobile("9876543210");
        assertEquals("9876543210", entity.getPassengerMobile());
    }

    @Test
    void testEqualsAndHashCode() {
        RideDetailsView entity1 = new RideDetailsView();
        RideDetailsView entity2 = new RideDetailsView();

        entity1.setRideDetailsKey("key123");
        entity2.setRideDetailsKey("key123");

        entity1.setPassengerId(1);
        entity2.setPassengerId(1);

        assertEquals(entity1, entity2);
        assertEquals(entity1.hashCode(), entity2.hashCode());
    }

    @Test
    void testToString() {
        RideDetailsView entity = new RideDetailsView();
        entity.setRideDetailsKey("key123");
        entity.setPassengerId(1);
        entity.setPublisherId(2);
        entity.setPublisherName("John Doe");
        entity.setPublisherMobile(9876543210L);
        entity.setFromLocation("New York");
        entity.setToLocation("Boston");
        entity.setNoOfSeats(3);
        entity.setDate(Date.valueOf("2023-06-22"));
        entity.setDepartureTime(Time.valueOf("10:00:00"));
        entity.setFare(50.0f);
        entity.setDistance(300.5f);
        entity.setJourneyHours(4.5f);
        entity.setPublisherStatus("ONGOING");
        entity.setPublisherRideId(5);
        entity.setPassengerRideId(6);
        entity.setPassengerCount(3);
        entity.setPublisherPaymentStatus("PAID");
        entity.setPassengerPaymentStatus("PENDING");
        entity.setPassengerMobile("9876543210");

        String expectedString = "RideDetailsView(rideDetailsKey=key123, passengerId=1, publisherId=2, publisherName=John Doe, publisherMobile=9876543210, fromLocation=New York, toLocation=Boston, noOfSeats=3, date=2023-06-22, departureTime=10:00:00, fare=50.0, distance=300.5, journeyHours=4.5, publisherStatus=ONGOING, publisherRideId=5, passengerRideId=6, passengerCount=3, publisherPaymentStatus=PAID, passengerPaymentStatus=PENDING, passengerMobile=9876543210)";
        assertEquals(expectedString, entity.toString());
    }
}
