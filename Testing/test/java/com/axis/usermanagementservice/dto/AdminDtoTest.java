package com.axis.usermanagementservice.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdminDtoTest {

    @Test
    void getAdminId() {
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminId(1);
        assertEquals(1, adminDto.getAdminId());
    }

    @Test
    void getEmail() {
        AdminDto adminDto = new AdminDto();
        adminDto.setEmail("test@test.com");
        assertEquals("test@test.com", adminDto.getEmail());
    }

    @Test
    void getAdminFullName() {
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminFullName("Admin Name");
        assertEquals("Admin Name", adminDto.getAdminFullName());
    }

    @Test
    void getUserType() {
        AdminDto adminDto = new AdminDto();
        assertEquals("ADMIN", adminDto.getUserType());
    }

    @Test
    void getToken() {
        AdminDto adminDto = new AdminDto();
        adminDto.setToken("sampleToken");
        assertEquals("sampleToken", adminDto.getToken());
    }

    @Test
    void setAdminId() {
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminId(1);
        assertEquals(1, adminDto.getAdminId());
    }

    @Test
    void setEmail() {
        AdminDto adminDto = new AdminDto();
        adminDto.setEmail("test@test.com");
        assertEquals("test@test.com", adminDto.getEmail());
    }

    @Test
    void setAdminFullName() {
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminFullName("Admin Name");
        assertEquals("Admin Name", adminDto.getAdminFullName());
    }

    @Test
    void setUserType() {
        AdminDto adminDto = new AdminDto();
        adminDto.setUserType("ADMIN");
        assertEquals("ADMIN", adminDto.getUserType());
    }

    @Test
    void setToken() {
        AdminDto adminDto = new AdminDto();
        adminDto.setToken("sampleToken");
        assertEquals("sampleToken", adminDto.getToken());
    }

    @Test
    void testEquals() {
        AdminDto adminDto1 = new AdminDto();
        adminDto1.setAdminId(1);
        adminDto1.setEmail("test@test.com");
        adminDto1.setAdminFullName("Admin Name");
        adminDto1.setUserType("ADMIN");
        adminDto1.setToken("sampleToken");

        AdminDto adminDto2 = new AdminDto();
        adminDto2.setAdminId(1);
        adminDto2.setEmail("test@test.com");
        adminDto2.setAdminFullName("Admin Name");
        adminDto2.setUserType("ADMIN");
        adminDto2.setToken("sampleToken");

        assertEquals(adminDto1, adminDto2);
    }

    @Test
    void canEqual() {
        AdminDto adminDto1 = new AdminDto();
        AdminDto adminDto2 = new AdminDto();
        assertTrue(adminDto1.canEqual(adminDto2));
    }

    @Test
    void testHashCode() {
        AdminDto adminDto1 = new AdminDto();
        adminDto1.setAdminId(1);
        adminDto1.setEmail("test@test.com");
        adminDto1.setAdminFullName("Admin Name");
        adminDto1.setUserType("ADMIN");
        adminDto1.setToken("sampleToken");

        AdminDto adminDto2 = new AdminDto();
        adminDto2.setAdminId(1);
        adminDto2.setEmail("test@test.com");
        adminDto2.setAdminFullName("Admin Name");
        adminDto2.setUserType("ADMIN");
        adminDto2.setToken("sampleToken");

        assertEquals(adminDto1.hashCode(), adminDto2.hashCode());
    }

    @Test
    void testToString() {
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminId(1);
        adminDto.setEmail("test@test.com");
        adminDto.setAdminFullName("Admin Name");
        adminDto.setUserType("ADMIN");
        adminDto.setToken("sampleToken");

        String expected = "AdminDto(adminId=1, email=test@test.com, adminFullName=Admin Name, userType=ADMIN, token=sampleToken)";
        assertEquals(expected, adminDto.toString());
    }
}
