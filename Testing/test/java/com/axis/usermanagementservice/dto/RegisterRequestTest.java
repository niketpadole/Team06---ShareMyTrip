package com.axis.usermanagementservice.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RegisterRequestTest {

    @Test
    void getAndSetEmail() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", request.getEmail());
    }

    @Test
    void getAndSetPassword() {
        RegisterRequest request = new RegisterRequest();
        request.setPassword("password123");
        assertEquals("password123", request.getPassword());
    }

    @Test
    void getAndSetAdminFullName() {
        RegisterRequest request = new RegisterRequest();
        request.setAdminFullName("John Doe");
        assertEquals("John Doe", request.getAdminFullName());
    }

    @Test
    void getAndSetUserType() {
        RegisterRequest request = new RegisterRequest();
        request.setUserType("ADMIN");
        assertEquals("ADMIN", request.getUserType());
    }

    @Test
    void testEqualsAndHashCode() {
        RegisterRequest request1 = new RegisterRequest();
        RegisterRequest request2 = new RegisterRequest();

        request1.setEmail("john.doe@example.com");
        request2.setEmail("john.doe@example.com");

        assertEquals(request1, request2);
        assertEquals(request1.hashCode(), request2.hashCode());
    }

    @Test
    void testToString() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("john.doe@example.com");
        request.setPassword("password123");
        request.setAdminFullName("John Doe");
        request.setUserType("ADMIN");

        String expectedString = "RegisterRequest(email=john.doe@example.com, password=password123, adminFullName=John Doe, userType=ADMIN)";
        assertEquals(expectedString, request.toString());
    }
}
