//package com.axis.usermanagementservice.dto;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import javax.validation.*;
//import java.util.Set;
//import static org.junit.jupiter.api.Assertions.*;
//
//class LoginRequestTest {
//
//    private Validator validator;
//
//    @BeforeEach
//    void setUp() {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
//    }
//
//    @Test
//    void getAndSetEmail() {
//        LoginRequest request = new LoginRequest();
//        request.setEmail("john.doe@example.com");
//        assertEquals("john.doe@example.com", request.getEmail());
//    }
//
//    @Test
//    void getAndSetPassword() {
//        LoginRequest request = new LoginRequest();
//        request.setPassword("password123");
//        assertEquals("password123", request.getPassword());
//    }
//
//    @Test
//    void testEqualsAndHashCode() {
//        LoginRequest request1 = new LoginRequest();
//        LoginRequest request2 = new LoginRequest();
//
//        request1.setEmail("john.doe@example.com");
//        request1.setPassword("password123");
//
//        request2.setEmail("john.doe@example.com");
//        request2.setPassword("password123");
//
//        assertEquals(request1, request2);
//        assertEquals(request1.hashCode(), request2.hashCode());
//    }
//
//    @Test
//    void testToString() {
//        LoginRequest request = new LoginRequest();
//        request.setEmail("john.doe@example.com");
//        request.setPassword("password123");
//
//        String expectedString = "LoginRequest(email=john.doe@example.com, password=password123)";
//        assertEquals(expectedString, request.toString());
//    }
//
//    @Test
//    void testValidation() {
//        LoginRequest request = new LoginRequest();
//        request.setEmail("invalid-email");
//        request.setPassword("");
//
//        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(request);
//        assertFalse(violations.isEmpty());
//
//        for (ConstraintViolation<LoginRequest> violation : violations) {
//            System.out.println(violation.getMessage());
//        }
//
//        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Invalid email format")));
//        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Password cannot be blank")));
//    }
//
//    @Test
//    void testValidLoginRequest() {
//        LoginRequest request = new LoginRequest();
//        request.setEmail("john.doe@example.com");
//        request.setPassword("password123");
//
//        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(request);
//        assertTrue(violations.isEmpty());
//    }
//}
