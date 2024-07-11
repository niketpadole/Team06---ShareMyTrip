package com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassengerRideTest {

    @Test
    void getAndSetPassengerRideId() {
        PassengerRide entity = new PassengerRide();
        entity.setPassengerRideId(1);
        assertEquals(1, entity.getPassengerRideId());
    }

    @Test
    void getAndSetPublisherRideId() {
        PassengerRide entity = new PassengerRide();
        entity.setPublisherRideId(2);
        assertEquals(2, entity.getPublisherRideId());
    }

    @Test
    void getAndSetPassengerId() {
        PassengerRide entity = new PassengerRide();
        entity.setPassengerId(3);
        assertEquals(3, entity.getPassengerId());
    }

    @Test
    void getAndSetNoOfPassengers() {
        PassengerRide entity = new PassengerRide();
        entity.setNoOfPassengers(4);
        assertEquals(4, entity.getNoOfPassengers());
    }

    @Test
    void getAndSetStatus() {
        PassengerRide entity = new PassengerRide();
        entity.setStatus("COMPLETED");
        assertEquals("COMPLETED", entity.getStatus());
    }

    @Test
    void getAndSetPaymentStatus() {
        PassengerRide entity = new PassengerRide();
        entity.setPaymentStatus("PAID");
        assertEquals("PAID", entity.getPaymentStatus());
    }

    @Test
    void testEqualsAndHashCode() {
        PassengerRide entity1 = new PassengerRide();
        PassengerRide entity2 = new PassengerRide();

        entity1.setPassengerRideId(1);
        entity2.setPassengerRideId(1);

        entity1.setPublisherRideId(2);
        entity2.setPublisherRideId(2);

        assertEquals(entity1, entity2);
        assertEquals(entity1.hashCode(), entity2.hashCode());
    }

    @Test
    void testToString() {
        PassengerRide entity = new PassengerRide();
        entity.setPassengerRideId(1);
        entity.setPublisherRideId(2);
        entity.setPassengerId(3);
        entity.setNoOfPassengers(4);
        entity.setStatus("NOT_COMPLETED");
        entity.setPaymentStatus("PENDING");

        String expectedString = "PassengerRide(passengerRideId=1, publisherRideId=2, passengerId=3, noOfPassengers=4, status=NOT_COMPLETED, paymentStatus=PENDING)";
        assertEquals(expectedString, entity.toString());
    }
}
