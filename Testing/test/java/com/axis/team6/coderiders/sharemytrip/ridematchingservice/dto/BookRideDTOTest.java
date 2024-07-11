package com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookRideDTOTest {

    @Test
    void getAndSetPublisherRideId() {
        BookRideDTO dto = new BookRideDTO();
        dto.setPublisherRideId(1);
        assertEquals(1, dto.getPublisherRideId());
    }

    @Test
    void getAndSetPassengerId() {
        BookRideDTO dto = new BookRideDTO();
        dto.setPassengerId(2);
        assertEquals(2, dto.getPassengerId());
    }

    @Test
    void getAndSetNoOfPassengers() {
        BookRideDTO dto = new BookRideDTO();
        dto.setNoOfPassengers(3);
        assertEquals(3, dto.getNoOfPassengers());
    }

    @Test
    void getAndSetStatus() {
        BookRideDTO dto = new BookRideDTO();
        dto.setStatus("COMPLETED");
        assertEquals("COMPLETED", dto.getStatus());
    }

    @Test
    void getAndSetPaymentStatus() {
        BookRideDTO dto = new BookRideDTO();
        dto.setPaymentStatus("PAID");
        assertEquals("PAID", dto.getPaymentStatus());
    }

    @Test
    void testEqualsAndHashCode() {
        BookRideDTO dto1 = new BookRideDTO();
        BookRideDTO dto2 = new BookRideDTO();

        dto1.setPublisherRideId(1);
        dto2.setPublisherRideId(1);

        dto1.setPassengerId(2);
        dto2.setPassengerId(2);

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        BookRideDTO dto = new BookRideDTO();
        dto.setPublisherRideId(1);
        dto.setPassengerId(2);
        dto.setNoOfPassengers(3);
        dto.setStatus("NOT_COMPLETED");
        dto.setPaymentStatus("PENDING");

        String expectedString = "BookRideDTO(publisherRideId=1, passengerId=2, noOfPassengers=3, status=NOT_COMPLETED, paymentStatus=PENDING)";
        assertEquals(expectedString, dto.toString());
    }
}
