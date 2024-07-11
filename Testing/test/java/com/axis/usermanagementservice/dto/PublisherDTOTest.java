package com.axis.usermanagementservice.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.sql.Timestamp;

class PublisherDTOTest {

    @Test
    void getAndSetPublisherId() {
        PublisherDTO dto = new PublisherDTO();
        dto.setPublisherId(1);
        assertEquals(1, dto.getPublisherId());
    }

    @Test
    void getAndSetFirstName() {
        PublisherDTO dto = new PublisherDTO();
        dto.setFirstName("John");
        assertEquals("John", dto.getFirstName());
    }

    @Test
    void getAndSetLastName() {
        PublisherDTO dto = new PublisherDTO();
        dto.setLastName("Doe");
        assertEquals("Doe", dto.getLastName());
    }

    @Test
    void getAndSetMobile() {
        PublisherDTO dto = new PublisherDTO();
        dto.setMobile(9876543210L);
        assertEquals(9876543210L, dto.getMobile());
    }

    @Test
    void getAndSetDateOfBirth() {
        PublisherDTO dto = new PublisherDTO();
        Date date = Date.valueOf("2000-01-01");
        dto.setDateOfBirth(date);
        assertEquals(date, dto.getDateOfBirth());
    }

    @Test
    void getAndSetEmail() {
        PublisherDTO dto = new PublisherDTO();
        dto.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", dto.getEmail());
    }

    @Test
    void getAndSetUserType() {
        PublisherDTO dto = new PublisherDTO();
        dto.setUserType("PUBLISHER");
        assertEquals("PUBLISHER", dto.getUserType());
    }

    @Test
    void getAndSetDrivingLicense() {
        PublisherDTO dto = new PublisherDTO();
        dto.setDrivingLicense("D123456789");
        assertEquals("D123456789", dto.getDrivingLicense());
    }

    @Test
    void getAndSetAadharCard() {
        PublisherDTO dto = new PublisherDTO();
        dto.setAadharCard("123412341234");
        assertEquals("123412341234", dto.getAadharCard());
    }

    @Test
    void getAndSetMiniBio() {
        PublisherDTO dto = new PublisherDTO();
        dto.setMiniBio("Sample bio");
        assertEquals("Sample bio", dto.getMiniBio());
    }

    @Test
    void getAndSetPassword() {
        PublisherDTO dto = new PublisherDTO();
        dto.setPassword("password123");
        assertEquals("password123", dto.getPassword());
    }

    @Test
    void getAndSetVehicleModelName() {
        PublisherDTO dto = new PublisherDTO();
        dto.setVehicleModelName("Model X");
        assertEquals("Model X", dto.getVehicleModelName());
    }

    @Test
    void getAndSetVehicleNo() {
        PublisherDTO dto = new PublisherDTO();
        dto.setVehicleNo("ABC1234");
        assertEquals("ABC1234", dto.getVehicleNo());
    }

    @Test
    void getAndSetTimestamp() {
        PublisherDTO dto = new PublisherDTO();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        dto.setTimestamp(timestamp);
        assertEquals(timestamp, dto.getTimestamp());
    }

    @Test
    void getAndSetTotalEarnings() {
        PublisherDTO dto = new PublisherDTO();
        dto.setTotalEarnings(1000.50f);
        assertEquals(1000.50f, dto.getTotalEarnings());
    }

    @Test
    void getAndSetToken() {
        PublisherDTO dto = new PublisherDTO();
        dto.setToken("token123");
        assertEquals("token123", dto.getToken());
    }

    @Test
    void testEqualsAndHashCode() {
        PublisherDTO dto1 = new PublisherDTO();
        PublisherDTO dto2 = new PublisherDTO();

        dto1.setPublisherId(1);
        dto2.setPublisherId(1);

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        PublisherDTO dto = new PublisherDTO();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setEmail("john.doe@example.com");

        String expectedString = "PublisherDTO(publisherId=0, firstName=John, lastName=Doe, mobile=null, dateOfBirth=null, email=john.doe@example.com, userType=PUBLISHER, drivingLicense=null, aadharCard=null, miniBio=null, password=null, vehicleModelName=null, vehicleNo=null, timestamp=null, totalEarnings=null, token=null)";
        assertEquals(expectedString, dto.toString());
    }
}
