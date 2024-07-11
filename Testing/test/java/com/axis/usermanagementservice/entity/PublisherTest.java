package com.axis.usermanagementservice.entity;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;

class PublisherTest {

    @Test
    void getAndSetPublisherId() {
        Publisher publisher = new Publisher();
        publisher.setPublisherId(1);
        assertEquals(1, publisher.getPublisherId());
    }

    @Test
    void getAndSetFirstName() {
        Publisher publisher = new Publisher();
        publisher.setFirstName("John");
        assertEquals("John", publisher.getFirstName());
    }

    @Test
    void getAndSetLastName() {
        Publisher publisher = new Publisher();
        publisher.setLastName("Doe");
        assertEquals("Doe", publisher.getLastName());
    }

    @Test
    void getAndSetMobile() {
        Publisher publisher = new Publisher();
        publisher.setMobile(9876543210L);
        assertEquals(9876543210L, publisher.getMobile());
    }

    @Test
    void getAndSetDateOfBirth() {
        Publisher publisher = new Publisher();
        Date date = Date.valueOf("2000-01-01");
        publisher.setDateOfBirth(date);
        assertEquals(date, publisher.getDateOfBirth());
    }

    @Test
    void getAndSetEmail() {
        Publisher publisher = new Publisher();
        publisher.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", publisher.getEmail());
    }

    @Test
    void getAndSetUserType() {
        Publisher publisher = new Publisher();
        publisher.setUserType("PUBLISHER");
        assertEquals("PUBLISHER", publisher.getUserType());
    }

    @Test
    void getAndSetDrivingLicense() {
        Publisher publisher = new Publisher();
        publisher.setDrivingLicense("D123456789");
        assertEquals("D123456789", publisher.getDrivingLicense());
    }

    @Test
    void getAndSetAadharCard() {
        Publisher publisher = new Publisher();
        publisher.setAadharCard("123412341234");
        assertEquals("123412341234", publisher.getAadharCard());
    }

    @Test
    void getAndSetMiniBio() {
        Publisher publisher = new Publisher();
        publisher.setMiniBio("Sample bio");
        assertEquals("Sample bio", publisher.getMiniBio());
    }

    @Test
    void getAndSetPassword() {
        Publisher publisher = new Publisher();
        publisher.setPassword("password123");
        assertEquals("password123", publisher.getPassword());
    }

    @Test
    void getAndSetVehicleModelName() {
        Publisher publisher = new Publisher();
        publisher.setVehicleModelName("Model X");
        assertEquals("Model X", publisher.getVehicleModelName());
    }

    @Test
    void getAndSetVehicleNo() {
        Publisher publisher = new Publisher();
        publisher.setVehicleNo("ABC1234");
        assertEquals("ABC1234", publisher.getVehicleNo());
    }

    @Test
    void getAndSetTimestamp() {
        Publisher publisher = new Publisher();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        publisher.setTimestamp(timestamp);
        assertEquals(timestamp, publisher.getTimestamp());
    }

    @Test
    void getAndSetTotalEarnings() {
        Publisher publisher = new Publisher();
        publisher.setTotalEarnings(1000.50f);
        assertEquals(1000.50f, publisher.getTotalEarnings());
    }

    @Test
    void testEqualsAndHashCode() {
        Publisher publisher1 = new Publisher();
        Publisher publisher2 = new Publisher();

        publisher1.setPublisherId(1);
        publisher2.setPublisherId(1);

        assertEquals(publisher1, publisher2);
        assertEquals(publisher1.hashCode(), publisher2.hashCode());
    }

    @Test
    void testToString() {
        Publisher publisher = new Publisher();
        publisher.setPublisherId(1);
        publisher.setFirstName("John");
        publisher.setLastName("Doe");
        publisher.setMobile(9876543210L);
        publisher.setDateOfBirth(Date.valueOf("2000-01-01"));
        publisher.setEmail("john.doe@example.com");
        publisher.setUserType("PUBLISHER");
        publisher.setDrivingLicense("D123456789");
        publisher.setAadharCard("123412341234");
        publisher.setMiniBio("Sample bio");
        publisher.setPassword("password123");
        publisher.setVehicleModelName("Model X");
        publisher.setVehicleNo("ABC1234");
        publisher.setTimestamp(new Timestamp(System.currentTimeMillis()));
        publisher.setTotalEarnings(1000.50f);

        String expectedString = "Publisher(publisherId=1, firstName=John, lastName=Doe, mobile=9876543210, dateOfBirth=2000-01-01, email=john.doe@example.com, userType=PUBLISHER, drivingLicense=D123456789, aadharCard=123412341234, miniBio=Sample bio, password=password123, vehicleModelName=Model X, vehicleNo=ABC1234, timestamp=" + publisher.getTimestamp() + ", totalEarnings=1000.5)";
        assertEquals(expectedString, publisher.toString());
    }
}
