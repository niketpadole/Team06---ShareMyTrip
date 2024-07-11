package com.axis.usermanagementservice.dto;

import org.junit.jupiter.api.Test;
import javax.validation.*;
import java.sql.Date;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class PassengerUpdateDTOTest {

    private Validator validator;

    public PassengerUpdateDTOTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void getAndSetFirstName() {
        PassengerUpdateDTO dto = new PassengerUpdateDTO();
        dto.setFirstName("John");
        assertEquals("John", dto.getFirstName());
    }

    @Test
    void getAndSetLastName() {
        PassengerUpdateDTO dto = new PassengerUpdateDTO();
        dto.setLastName("Doe");
        assertEquals("Doe", dto.getLastName());
    }

    @Test
    void getAndSetMobile() {
        PassengerUpdateDTO dto = new PassengerUpdateDTO();
        dto.setMobile(9876543210L);
        assertEquals(9876543210L, dto.getMobile());
    }

    @Test
    void getAndSetDateOfBirth() {
        PassengerUpdateDTO dto = new PassengerUpdateDTO();
        Date date = Date.valueOf("2000-01-01");
        dto.setDateOfBirth(date);
        assertEquals(date, dto.getDateOfBirth());
    }

    @Test
    void getAndSetAadharCard() {
        PassengerUpdateDTO dto = new PassengerUpdateDTO();
        dto.setAadharCard("123412341234");
        assertEquals("123412341234", dto.getAadharCard());
    }

    @Test
    void getAndSetMiniBio() {
        PassengerUpdateDTO dto = new PassengerUpdateDTO();
        dto.setMiniBio("Sample bio");
        assertEquals("Sample bio", dto.getMiniBio());
    }

    @Test
    void testEqualsAndHashCode() {
        PassengerUpdateDTO dto1 = new PassengerUpdateDTO();
        PassengerUpdateDTO dto2 = new PassengerUpdateDTO();

        dto1.setAadharCard("123412341234");
        dto2.setAadharCard("123412341234");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        PassengerUpdateDTO dto = new PassengerUpdateDTO();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setAadharCard("123412341234");

        String expectedString = "PassengerUpdateDTO(firstName=John, lastName=Doe, mobile=0, dateOfBirth=null, aadharCard=123412341234, miniBio=null)";
        assertEquals(expectedString, dto.toString());
    }

//    @Test
//    void testValidation() {
//        PassengerUpdateDTO dto = new PassengerUpdateDTO();
//        dto.setFirstName("John");
//        dto.setLastName("Doe");
//        dto.setMobile(9876543210L);
//        dto.setDateOfBirth(Date.valueOf("2000-01-01"));
//        dto.setAadharCard("123456789012");
//
//        Set<ConstraintViolation<PassengerUpdateDTO>> violations = validator.validate(dto);
//        assertTrue(violations.isEmpty());
//    }

//    @Test
//    void testInvalidMobile() {
//        PassengerUpdateDTO dto = new PassengerUpdateDTO();
//        dto.setMobile(1234567890L); // Invalid mobile number
//
//        Set<ConstraintViolation<PassengerUpdateDTO>> violations = validator.validate(dto);
//        assertFalse(violations.isEmpty());
//        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Invalid mobile number")));
//    }

//    @Test
//    void testBlankMobile() {
//        PassengerUpdateDTO dto = new PassengerUpdateDTO();
//        dto.setMobile(0); // Blank mobile number (as long)
//
//        Set<ConstraintViolation<PassengerUpdateDTO>> violations = validator.validate(dto);
//        assertFalse(violations.isEmpty());
//        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Mobile cannot be blank")));
//    }
}
