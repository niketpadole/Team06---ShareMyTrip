package com.axis.usermanagementservice.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthResponseTest {

    @Test
    void getToken() {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken("testToken");
        assertEquals("testToken", authResponse.getToken());
    }

    @Test
    void setToken() {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken("testToken");
        assertEquals("testToken", authResponse.getToken());
    }

    @Test
    void testEquals() {
        AuthResponse authResponse1 = new AuthResponse("testToken");
        AuthResponse authResponse2 = new AuthResponse("testToken");

        assertEquals(authResponse1, authResponse2);
    }

    @Test
    void canEqual() {
        AuthResponse authResponse1 = new AuthResponse();
        AuthResponse authResponse2 = new AuthResponse();
        assertTrue(authResponse1.canEqual(authResponse2));
    }

    @Test
    void testHashCode() {
        AuthResponse authResponse1 = new AuthResponse("testToken");
        AuthResponse authResponse2 = new AuthResponse("testToken");

        assertEquals(authResponse1.hashCode(), authResponse2.hashCode());
    }

    @Test
    void testToString() {
        AuthResponse authResponse = new AuthResponse("testToken");

        String expected = "AuthResponse(token=testToken)";
        assertEquals(expected, authResponse.toString());
    }
}
