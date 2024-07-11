//package com.axis.usermanagementservice.dto;
//
//import org.junit.jupiter.api.Test;
//import javax.validation.*;
//import java.sql.Date;
//import java.util.Set;
//import static org.junit.jupiter.api.Assertions.*;
//
//class PublisherRegistrationRequestTest {
//
//    private Validator validator;
//
//    public PublisherRegistrationRequestTest() {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
//    }
//
//    @Test
//    void getAndSetFirstName() {
//        PublisherRegistrationRequest request = new PublisherRegistrationRequest();
//        request.setFirstName("John");
//        assertEquals("John", request.getFirstName());
//    }
//
//    @Test
//    void getAndSetLastName() {
//        PublisherRegistrationRequest request = new PublisherRegistrationRequest();
//        request.setLastName("Doe");
//        assertEquals("Doe", request.getLastName());
//    }
//
//    @Test
//    void getAndSetMobile() {
//        PublisherRegistrationRequest request = new PublisherRegistrationRequest();
//        request.setMobile(9876543210L);
//        assertEquals(9876543210L, request.getMobile());
//    }
//
//    @Test
//    void getAndSetDateOfBirth() {
//        PublisherRegistrationRequest request = new PublisherRegistrationRequest();
//        Date date = Date.valueOf("2000-01-01");
//        request.setDateOfBirth(date);
//        assertEquals(date, request.getDateOfBirth());
//    }
//
//    @Test
//    void getAndSetEmail() {
//        PublisherRegistrationRequest request = new PublisherRegistrationRequest();
//        request.setEmail("john.doe@example.com");
//        assertEquals("john.doe@example.com", request.getEmail());
//    }
//
//    @Test
//    void getAndSetDrivingLicense() {
//        PublisherRegistrationRequest request = new PublisherRegistrationRequest();
//        request.setDrivingLicense("D123456789");
//        assertEquals("D123456789", request.getDrivingLicense());
//    }
//
//    @Test
//    void getAndSetAadharCard() {
//        PublisherRegistrationRequest request = new PublisherRegistrationRequest();
//        request.setAadharCard("123412341234");
//        assertEquals("123412341234", request.getAadharCard());
//    }
//
//    @Test
//    void getAndSetMiniBio() {
//        PublisherRegistrationRequest request = new PublisherRegistrationRequest();
//        request.setMiniBio("Sample bio");
//        assertEquals("Sample bio", request.getMiniBio());
//    }
//
//    @Test
//    void getAndSetPassword() {
//        PublisherRegistrationRequest request = new PublisherRegistrationRequest();
//        request.setPassword("password123");
//        assertEquals("password123", request.getPassword());
//    }
//
//    @Test
//    void getAndSetVehicleModelName() {
//        PublisherRegistrationRequest request = new PublisherRegistrationRequest();
//        request.setVehicleModelName("Model X");
//        assertEquals("Model X", request.getVehicleModelName());
//    }
//
//    @Test
//    void getAndSetVehicleNo() {
//        PublisherRegistrationRequest request = new PublisherRegistrationRequest();
//        request.setVehicleNo("ABC1234");
//        assertEquals("ABC1234", request.getVehicleNo());
//    }
//
//    @Test
//    void testEqualsAndHashCode() {
//        PublisherRegistrationRequest request1 = new PublisherRegistrationRequest();
//        PublisherRegistrationRequest request2 = new PublisherRegistrationRequest();
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
//        PublisherRegistrationRequest request = new PublisherRegistrationRequest();
//        request.setFirstName("John");
//        request.setLastName("Doe");
//        request.setEmail("john.doe@example.com");
//
//        String expectedString = "PublisherRegistrationRequest(firstName=John, lastName=Doe, mobile=0, dateOfBirth=null, email=john.doe@example.com, drivingLicense=null, aadharCard=null, miniBio=null, password=null, vehicleModelName=null, vehicleNo=null)";
//        assertEquals(expectedString, request.toString());
//    }
//
//    @Test
//    void testValidation() {
//        PublisherRegistrationRequest request = new PublisherRegistrationRequest();
//        request.setFirstName("John");
//        request.setLastName("Doe");
//        request.setMobile(9876543210L);
//        request.setDateOfBirth(Date.valueOf("2000-01-01"));
//        request.setEmail("invalid-email");
//        request.setPassword("password123");
//
//        Set<ConstraintViolation<PublisherRegistrationRequest>> violations = validator.validate(request);
//        assertFalse(violations.isEmpty());
//
//        for (ConstraintViolation<PublisherRegistrationRequest> violation : violations) {
//            System.out.println(violation.getMessage());
//        }
//
//        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Invalid email format")));
//    }
//
//    @Test
//    void testInvalidMobile() {
//        PublisherRegistrationRequest request = new PublisherRegistrationRequest();
//        request.setMobile(1234567890L); // Invalid mobile number
//
//        Set<ConstraintViolation<PublisherRegistrationRequest>> violations = validator.validate(request);
//        assertFalse(violations.isEmpty());
//        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Invalid mobile number")));
//    }
//
//    @Test
//    void testBlankMobile() {
//        PublisherRegistrationRequest request = new PublisherRegistrationRequest();
//        request.setMobile(0); // Blank mobile number (as long)
//
//        Set<ConstraintViolation<PublisherRegistrationRequest>> violations = validator.validate(request);
//        assertFalse(violations.isEmpty());
//        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Mobile cannot be blank")));
//    }
//}
