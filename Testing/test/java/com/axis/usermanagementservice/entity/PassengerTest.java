package com.axis.usermanagementservice.entity;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    @Test
    void getAndSetPassengerId() {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(1);
        assertEquals(1, passenger.getPassengerId());
    }

    @Test
    void getAndSetFirstName() {
        Passenger passenger = new Passenger();
        passenger.setFirstName("John");
        assertEquals("John", passenger.getFirstName());
    }

    @Test
    void getAndSetLastName() {
        Passenger passenger = new Passenger();
        passenger.setLastName("Doe");
        assertEquals("Doe", passenger.getLastName());
    }

    @Test
    void getAndSetMobile() {
        Passenger passenger = new Passenger();
        passenger.setMobile(9876543210L);
        assertEquals(9876543210L, passenger.getMobile());
    }

    @Test
    void getAndSetDateOfBirth() {
        Passenger passenger = new Passenger();
        Date date = Date.valueOf("2000-01-01");
        passenger.setDateOfBirth(date);
        assertEquals(date, passenger.getDateOfBirth());
    }

    @Test
    void getAndSetEmail() {
        Passenger passenger = new Passenger();
        passenger.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", passenger.getEmail());
    }

    @Test
    void getAndSetUserType() {
        Passenger passenger = new Passenger();
        passenger.setUserType("PASSENGER");
        assertEquals("PASSENGER", passenger.getUserType());
    }

    @Test
    void getAndSetAadharCard() {
        Passenger passenger = new Passenger();
        passenger.setAadharCard("123412341234");
        assertEquals("123412341234", passenger.getAadharCard());
    }

    @Test
    void getAndSetMiniBio() {
        Passenger passenger = new Passenger();
        passenger.setMiniBio("Sample bio");
        assertEquals("Sample bio", passenger.getMiniBio());
    }

    @Test
    void getAndSetPassword() {
        Passenger passenger = new Passenger();
        passenger.setPassword("password123");
        assertEquals("password123", passenger.getPassword());
    }

    @Test
    void getAndSetTimestamp() {
        Passenger passenger = new Passenger();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        passenger.setTimestamp(timestamp);
        assertEquals(timestamp, passenger.getTimestamp());
    }

    @Test
    void testEqualsAndHashCode() {
        Passenger passenger1 = new Passenger();
        Passenger passenger2 = new Passenger();

        passenger1.setPassengerId(1);
        passenger2.setPassengerId(1);

        assertEquals(passenger1, passenger2);
        assertEquals(passenger1.hashCode(), passenger2.hashCode());
    }

    @Test
    void testToString() {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(1);
        passenger.setFirstName("John");
        passenger.setLastName("Doe");
        passenger.setMobile(9876543210L);
        passenger.setDateOfBirth(Date.valueOf("2000-01-01"));
        passenger.setEmail("john.doe@example.com");
        passenger.setUserType("PASSENGER");
        passenger.setAadharCard("123412341234");
        passenger.setMiniBio("Sample bio");
        passenger.setPassword("password123");
        passenger.setTimestamp(new Timestamp(System.currentTimeMillis()));

        String expectedString = "Passenger(passengerId=1, firstName=John, lastName=Doe, mobile=9876543210, dateOfBirth=2000-01-01, email=john.doe@example.com, userType=PASSENGER, aadharCard=123412341234, miniBio=Sample bio, password=password123, timestamp=" + passenger.getTimestamp() + ")";
        assertEquals(expectedString, passenger.toString());
    }
}
