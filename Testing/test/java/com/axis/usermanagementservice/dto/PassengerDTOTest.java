package com.axis.usermanagementservice.dto;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;

class PassengerDTOTest {

    @Test
    void getAndSetPassengerId() {
        PassengerDTO dto = new PassengerDTO();
        dto.setPassengerId(1);
        assertEquals(1, dto.getPassengerId());
    }

    @Test
    void getAndSetFirstName() {
        PassengerDTO dto = new PassengerDTO();
        dto.setFirstName("John");
        assertEquals("John", dto.getFirstName());
    }

    @Test
    void getAndSetLastName() {
        PassengerDTO dto = new PassengerDTO();
        dto.setLastName("Doe");
        assertEquals("Doe", dto.getLastName());
    }

    @Test
    void getAndSetMobile() {
        PassengerDTO dto = new PassengerDTO();
        dto.setMobile(9876543210L);
        assertEquals(9876543210L, dto.getMobile());
    }

    @Test
    void getAndSetDateOfBirth() {
        PassengerDTO dto = new PassengerDTO();
        Date date = Date.valueOf("2000-01-01");
        dto.setDateOfBirth(date);
        assertEquals(date, dto.getDateOfBirth());
    }

    @Test
    void getAndSetEmail() {
        PassengerDTO dto = new PassengerDTO();
        dto.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", dto.getEmail());
    }

    @Test
    void getAndSetUserType() {
        PassengerDTO dto = new PassengerDTO();
        dto.setUserType("PASSENGER");
        assertEquals("PASSENGER", dto.getUserType());
    }

    @Test
    void getAndSetAadharCard() {
        PassengerDTO dto = new PassengerDTO();
        dto.setAadharCard("123412341234");
        assertEquals("123412341234", dto.getAadharCard());
    }

    @Test
    void getAndSetMiniBio() {
        PassengerDTO dto = new PassengerDTO();
        dto.setMiniBio("Sample bio");
        assertEquals("Sample bio", dto.getMiniBio());
    }

    @Test
    void getAndSetPassword() {
        PassengerDTO dto = new PassengerDTO();
        dto.setPassword("password123");
        assertEquals("password123", dto.getPassword());
    }

    @Test
    void getAndSetTimestamp() {
        PassengerDTO dto = new PassengerDTO();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        dto.setTimestamp(timestamp);
        assertEquals(timestamp, dto.getTimestamp());
    }

    @Test
    void getAndSetToken() {
        PassengerDTO dto = new PassengerDTO();
        dto.setToken("token123");
        assertEquals("token123", dto.getToken());
    }

    @Test
    void testEqualsAndHashCode() {
        PassengerDTO dto1 = new PassengerDTO();
        PassengerDTO dto2 = new PassengerDTO();

        dto1.setPassengerId(1);
        dto2.setPassengerId(1);

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        PassengerDTO dto = new PassengerDTO();
        dto.setPassengerId(1);
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setMobile(9876543210L);
        dto.setDateOfBirth(Date.valueOf("2000-01-01"));
        dto.setEmail("john.doe@example.com");
        dto.setUserType("PASSENGER");
        dto.setAadharCard("123412341234");
        dto.setMiniBio("Sample bio");
        dto.setPassword("password123");
        dto.setTimestamp(new Timestamp(System.currentTimeMillis()));
        dto.setToken("token123");

        String expectedString = "PassengerDTO(passengerId=1, firstName=John, lastName=Doe, mobile=9876543210, dateOfBirth=2000-01-01, email=john.doe@example.com, userType=PASSENGER, aadharCard=123412341234, miniBio=Sample bio, password=password123, timestamp=" + dto.getTimestamp() + ", token=token123)";
        assertEquals(expectedString, dto.toString());
    }
}
