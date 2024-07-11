package com.axis.usermanagementservice.dto;

import org.junit.jupiter.api.Test;
import javax.validation.*;
import java.sql.Date;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class PublisherUpdateDTOTest {

    private Validator validator;

    public PublisherUpdateDTOTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void getAndSetFirstName() {
        PublisherUpdateDTO dto = new PublisherUpdateDTO();
        dto.setFirstName("John");
        assertEquals("John", dto.getFirstName());
    }

    @Test
    void getAndSetLastName() {
        PublisherUpdateDTO dto = new PublisherUpdateDTO();
        dto.setLastName("Doe");
        assertEquals("Doe", dto.getLastName());
    }

    @Test
    void getAndSetMobile() {
        PublisherUpdateDTO dto = new PublisherUpdateDTO();
        dto.setMobile(9876543210L);
        assertEquals(9876543210L, dto.getMobile());
    }

    @Test
    void getAndSetDateOfBirth() {
        PublisherUpdateDTO dto = new PublisherUpdateDTO();
        Date date = Date.valueOf("2000-01-01");
        dto.setDateOfBirth(date);
        assertEquals(date, dto.getDateOfBirth());
    }

    @Test
    void getAndSetAadharCard() {
        PublisherUpdateDTO dto = new PublisherUpdateDTO();
        dto.setAadharCard("123412341234");
        assertEquals("123412341234", dto.getAadharCard());
    }

    @Test
    void getAndSetMiniBio() {
        PublisherUpdateDTO dto = new PublisherUpdateDTO();
        dto.setMiniBio("Sample bio");
        assertEquals("Sample bio", dto.getMiniBio());
    }

    @Test
    void getAndSetVehicleModelName() {
        PublisherUpdateDTO dto = new PublisherUpdateDTO();
        dto.setVehicleModelName("Model X");
        assertEquals("Model X", dto.getVehicleModelName());
    }

    @Test
    void getAndSetVehicleNo() {
        PublisherUpdateDTO dto = new PublisherUpdateDTO();
        dto.setVehicleNo("ABC1234");
        assertEquals("ABC1234", dto.getVehicleNo());
    }

    @Test
    void testEqualsAndHashCode() {
        PublisherUpdateDTO dto1 = new PublisherUpdateDTO();
        PublisherUpdateDTO dto2 = new PublisherUpdateDTO();

        dto1.setMobile(9876543210L);
        dto2.setMobile(9876543210L);

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        PublisherUpdateDTO dto = new PublisherUpdateDTO();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setMobile(9876543210L);
        dto.setDateOfBirth(Date.valueOf("2000-01-01"));
        dto.setAadharCard("123412341234");
        dto.setMiniBio("Sample bio");
        dto.setVehicleModelName("Model X");
        dto.setVehicleNo("ABC1234");

        String expectedString = "PublisherUpdateDTO(firstName=John, lastName=Doe, mobile=9876543210, dateOfBirth=2000-01-01, aadharCard=123412341234, miniBio=Sample bio, vehicleModelName=Model X, vehicleNo=ABC1234)";
        assertEquals(expectedString, dto.toString());
    }

//    @Test
//    void testValidation() {
//        PublisherUpdateDTO dto = new PublisherUpdateDTO();
//        dto.setFirstName("John");
//        dto.setLastName("Doe");
//        dto.setMobile(9876543210L);
//        dto.setDateOfBirth(Date.valueOf("2000-01-01"));
//        dto.setAadharCard("123456789012");
//        dto.setMiniBio("Sample bio");
//        dto.setVehicleModelName("Model X");
//        dto.setVehicleNo("ABC1234");
//
//        Set<ConstraintViolation<PublisherUpdateDTO>> violations = validator.validate(dto);
//        assertTrue(violations.isEmpty());
//    }

//    @Test
//    void testInvalidMobile() {
//        PublisherUpdateDTO dto = new PublisherUpdateDTO();
//        dto.setMobile(1234567890L); // Invalid mobile number
//
//        Set<ConstraintViolation<PublisherUpdateDTO>> violations = validator.validate(dto);
//        assertFalse(violations.isEmpty());
//        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Invalid mobile number")));
//    }

//    @Test
//    void testBlankMobile() {
//        PublisherUpdateDTO dto = new PublisherUpdateDTO();
//        dto.setMobile(null); // Blank mobile number
//
//        Set<ConstraintViolation<PublisherUpdateDTO>> violations = validator.validate(dto);
//        assertFalse(violations.isEmpty());
//        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Mobile cannot be blank")));
//    }
}
