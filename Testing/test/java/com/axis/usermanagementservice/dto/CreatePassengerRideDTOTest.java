package com.axis.usermanagementservice.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreatePassengerRideDTOTest {

    @Test
    void getAndSetPassengerId() {
        CreatePassengerRideDTO dto = new CreatePassengerRideDTO();
        dto.setPassengerId(1);
        assertEquals(1, dto.getPassengerId());
    }

    @Test
    void getAndSetPublisherRideId() {
        CreatePassengerRideDTO dto = new CreatePassengerRideDTO();
        dto.setPublisherRideId(101);
        assertEquals(101, dto.getPublisherRideId());
    }

    @Test
    void getAndSetNoOfPassengers() {
        CreatePassengerRideDTO dto = new CreatePassengerRideDTO();
        dto.setNoOfPassengers(3);
        assertEquals(3, dto.getNoOfPassengers());
    }

    @Test
    void getAndSetPaymentStatus() {
        CreatePassengerRideDTO dto = new CreatePassengerRideDTO();
        dto.setPaymentStatus("PAID");
        assertEquals("PAID", dto.getPaymentStatus());
    }

    @Test
    void testEqualsAndHashCode() {
        CreatePassengerRideDTO dto1 = new CreatePassengerRideDTO();
        CreatePassengerRideDTO dto2 = new CreatePassengerRideDTO();

        dto1.setPassengerId(1);
        dto2.setPassengerId(1);

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        CreatePassengerRideDTO dto = new CreatePassengerRideDTO();
        dto.setPassengerId(1);
        dto.setPublisherRideId(101);
        dto.setNoOfPassengers(3);
        dto.setPaymentStatus("PAID");

        String expectedString = "CreatePassengerRideDTO(passengerId=1, publisherRideId=101, noOfPassengers=3, paymentStatus=PAID)";
        assertEquals(expectedString, dto.toString());
    }
}
