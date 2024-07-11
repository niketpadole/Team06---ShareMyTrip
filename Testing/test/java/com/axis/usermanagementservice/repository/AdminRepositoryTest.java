//package com.axis.usermanagementservice.repository;
//
//import com.axis.usermanagementservice.entity.Admin;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//class AdminRepositoryTest {
//
//    @Autowired
//    private AdminRepository adminRepository;
//
//    @Test
//    void findByEmail() {
//        // Given
//        Admin admin = new Admin();
//        admin.setEmail("john.doe@example.com");
//        admin.setPassword("password123");
//        admin.setAdminFullName("John Doe");
//        adminRepository.save(admin);
//
//        // When
//        Admin foundAdmin = adminRepository.findByEmail("john.doe@example.com");
//
//        // Then
//        assertNotNull(foundAdmin);
//        assertEquals("john.doe@example.com", foundAdmin.getEmail());
//        assertEquals("password123", foundAdmin.getPassword());
//        assertEquals("John Doe", foundAdmin.getAdminFullName());
//    }
//}
