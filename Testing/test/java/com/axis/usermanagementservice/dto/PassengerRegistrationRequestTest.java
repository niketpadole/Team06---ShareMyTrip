//package com.axis.usermanagementservice.dto;
//
//import org.junit.jupiter.api.Test;
//import javax.validation.*;
//import java.sql.Date;
//import java.util.Set;
//import static org.junit.jupiter.api.Assertions.*;
//
//class PassengerRegistrationRequestTest {
//
//    private Validator validator;
//
//    public PassengerRegistrationRequestTest() {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
//    }
//
//    @Test
//    void getAndSetFirstName() {
//        PassengerRegistrationRequest request = new PassengerRegistrationRequest();
//        request.setFirstName("John");
//        assertEquals("John", request.getFirstName());
//    }
//
//    @Test
//    void getAndSetLastName() {
//        PassengerRegistrationRequest request = new PassengerRegistrationRequest();
//        request.setLastName("Doe");
//        assertEquals("Doe", request.getLastName());
//    }
//
//    @Test
//    void getAndSetMobile() {
//        PassengerRegistrationRequest request = new PassengerRegistrationRequest();
//        request.setMobile(9876543210L);
//        assertEquals(9876543210L, request.getMobile());
//    }
//
//    @Test
//    void getAndSetDateOfBirth() {
//        PassengerRegistrationRequest request = new PassengerRegistrationRequest();
//        Date date = Date.valueOf("2000-01-01");
//        request.setDateOfBirth(date);
//        assertEquals(date, request.getDateOfBirth());
//    }
//
//    @Test
//    void getAndSetEmail() {
//        PassengerRegistrationRequest request = new PassengerRegistrationRequest();
//        request.setEmail("john.doe@example.com");
//        assertEquals("john.doe@example.com", request.getEmail());
//    }
//
//    @Test
//    void getAndSetAadharCard() {
//        PassengerRegistrationRequest request = new PassengerRegistrationRequest();
//        request.setAadharCard("123412341234");
//        assertEquals("123412341234", request.getAadharCard());
//    }
//
//    @Test
//    void getAndSetMiniBio() {
//        PassengerRegistrationRequest request = new PassengerRegistrationRequest();
//        request.setMiniBio("Sample bio");
//        assertEquals("Sample bio", request.getMiniBio());
//    }
//
//    @Test
//    void getAndSetPassword() {
//        PassengerRegistrationRequest request = new PassengerRegistrationRequest();
//        request.setPassword("password123");
//        assertEquals("password123", request.getPassword());
//    }
//
//    @Test
//    void testEqualsAndHashCode() {
//        PassengerRegistrationRequest request1 = new PassengerRegistrationRequest();
//        PassengerRegistrationRequest request2 = new PassengerRegistrationRequest();
//
//        request1.setEmail("john.doe@example.com");
//        request2.setEmail("john.doe@example.com");
//
//        assertEquals(request1, request2);
//        assertEquals(request1.hashCode(), request2.hashCode());
//    }
//
//    @Test
//    void testToString() {
//        PassengerRegistrationRequest request = new PassengerRegistrationRequest();
//        request.setFirstName("John");
//        request.setLastName("Doe");
//        request.setEmail("john.doe@example.com");
//
//        String expectedString = "PassengerRegistrationRequest(firstName=John, lastName=Doe, mobile=0, dateOfBirth=null, email=john.doe@example.com, aadharCard=null, miniBio=null, password=null)";
//        assertEquals(expectedString, request.toString());
//    }
//
//    @Test
//    void testValidation() {
//        PassengerRegistrationRequest request = new PassengerRegistrationRequest();
//        request.setFirstName("John");
//        request.setLastName("Doe");
//        request.setMobile(9876543210L);
//        request.setDateOfBirth(Date.valueOf("2000-01-01"));
//        request.setEmail("invalid-email");
//        request.setAadharCard("123456789012");
//        request.setPassword("password123");
//
//        Set<ConstraintViolation<PassengerRegistrationRequest>> violations = validator.validate(request);
//        assertFalse(violations.isEmpty());
//
//        for (ConstraintViolation<PassengerRegistrationRequest> violation : violations) {
//            System.out.println(violation.getMessage());
//        }
//
//        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Invalid email format")));
//    }
//}
