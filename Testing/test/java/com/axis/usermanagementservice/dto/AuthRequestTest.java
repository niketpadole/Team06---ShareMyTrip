package com.axis.usermanagementservice.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthRequestTest {

    @Test
    void getEmail() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("test@test.com");
        assertEquals("test@test.com", authRequest.getEmail());
    }

    @Test
    void getPassword() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setPassword("password");
        assertEquals("password", authRequest.getPassword());
    }

    @Test
    void setEmail() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("test@test.com");
        assertEquals("test@test.com", authRequest.getEmail());
    }

    @Test
    void setPassword() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setPassword("password");
        assertEquals("password", authRequest.getPassword());
    }

    @Test
    void testEquals() {
        AuthRequest authRequest1 = new AuthRequest("test@test.com", "password");
        AuthRequest authRequest2 = new AuthRequest("test@test.com", "password");

        assertEquals(authRequest1, authRequest2);
    }

    @Test
    void canEqual() {
        AuthRequest authRequest1 = new AuthRequest();
        AuthRequest authRequest2 = new AuthRequest();
        assertTrue(authRequest1.canEqual(authRequest2));
    }

    @Test
    void testHashCode() {
        AuthRequest authRequest1 = new AuthRequest("test@test.com", "password");
        AuthRequest authRequest2 = new AuthRequest("test@test.com", "password");

        assertEquals(authRequest1.hashCode(), authRequest2.hashCode());
    }

    @Test
    void testToString() {
        AuthRequest authRequest = new AuthRequest("test@test.com", "password");

        String expected = "AuthRequest(email=test@test.com, password=password)";
        assertEquals(expected, authRequest.toString());
    }
}
