package com.axis.usermanagementservice.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    @Test
    void getAdminId() {
        Admin admin = new Admin();
        admin.setAdminId(1);
        assertEquals(1, admin.getAdminId());
    }

    @Test
    void getEmail() {
        Admin admin = new Admin();
        admin.setEmail("admin@example.com");
        assertEquals("admin@example.com", admin.getEmail());
    }

    @Test
    void getPassword() {
        Admin admin = new Admin();
        admin.setPassword("password123");
        assertEquals("password123", admin.getPassword());
    }

    @Test
    void getAdminFullName() {
        Admin admin = new Admin();
        admin.setAdminFullName("John Doe");
        assertEquals("John Doe", admin.getAdminFullName());
    }

    @Test
    void getUserType() {
        Admin admin = new Admin();
        admin.setUserType("ADMIN");
        assertEquals("ADMIN", admin.getUserType());
    }

    @Test
    void setAdminId() {
        Admin admin = new Admin();
        admin.setAdminId(1);
        assertEquals(1, admin.getAdminId());
    }

    @Test
    void setEmail() {
        Admin admin = new Admin();
        admin.setEmail("admin@example.com");
        assertEquals("admin@example.com", admin.getEmail());
    }

    @Test
    void setPassword() {
        Admin admin = new Admin();
        admin.setPassword("password123");
        assertEquals("password123", admin.getPassword());
    }

    @Test
    void setAdminFullName() {
        Admin admin = new Admin();
        admin.setAdminFullName("John Doe");
        assertEquals("John Doe", admin.getAdminFullName());
    }

    @Test
    void setUserType() {
        Admin admin = new Admin();
        admin.setUserType("ADMIN");
        assertEquals("ADMIN", admin.getUserType());
    }

    @Test
    void testEquals() {
        Admin admin1 = new Admin(1, "admin@example.com", "password123", "John Doe", "ADMIN");
        Admin admin2 = new Admin(1, "admin@example.com", "password123", "John Doe", "ADMIN");
        assertEquals(admin1, admin2);
    }

    @Test
    void canEqual() {
        Admin admin1 = new Admin();
        Admin admin2 = new Admin();
        assertTrue(admin1.canEqual(admin2));
    }

    @Test
    void testHashCode() {
        Admin admin1 = new Admin(1, "admin@example.com", "password123", "John Doe", "ADMIN");
        Admin admin2 = new Admin(1, "admin@example.com", "password123", "John Doe", "ADMIN");
        assertEquals(admin1.hashCode(), admin2.hashCode());
    }

    @Test
    void testToString() {
        Admin admin = new Admin(1, "admin@example.com", "password123", "John Doe", "ADMIN");
        String expected = "Admin(adminId=1, email=admin@example.com, password=password123, adminFullName=John Doe, userType=ADMIN)";
        assertEquals(expected, admin.toString());
    }
}
