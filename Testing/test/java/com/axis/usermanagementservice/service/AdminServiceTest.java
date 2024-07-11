package com.axis.usermanagementservice.service;

import com.axis.usermanagementservice.dto.AdminDto;
import com.axis.usermanagementservice.entity.Admin;
import com.axis.usermanagementservice.repository.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminServiceTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private AdminRepository adminRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAdmins() {
        List<Admin> admins = Arrays.asList(new Admin(), new Admin());
        when(adminRepository.findAll()).thenReturn(admins);

        List<Admin> result = adminService.getAllAdmins();
        assertEquals(2, result.size());
        verify(adminRepository, times(1)).findAll();
    }

    @Test
    void getAdminById() {
        Admin admin = new Admin();
        when(adminRepository.findById(1)).thenReturn(Optional.of(admin));

        Optional<Admin> result = adminService.getAdminById(1);
        assertTrue(result.isPresent());
        assertEquals(admin, result.get());
        verify(adminRepository, times(1)).findById(1);
    }

//    @Test
//    void saveAdmin() {
//        Admin admin = new Admin();
//        when(adminRepository.save(admin)).thenReturn(admin);
//
//        Admin result = adminService.saveAdmin(admin);
//        assertEquals(admin, result);
//        verify(adminRepository, times(1)).save(admin);
//    }

//    @Test
//    void updateAdmin() {
//        Admin admin = new Admin();
//        when(adminRepository.save(admin)).thenReturn(admin);
//
//        Admin result = adminService.updateAdmin(admin);
//        assertEquals(admin, result);
//        verify(adminRepository, times(1)).save(admin);
//    }

    @Test
    void deleteAdmin() {
        doNothing().when(adminRepository).deleteById(1);

        adminService.deleteAdmin(1);
        verify(adminRepository, times(1)).deleteById(1);
    }

//    @Test
//    void loginAdmin() {
//        Admin admin = new Admin();
//        admin.setEmail("admin@test.com");
//        admin.setPassword("password");
//        when(adminRepository.findByEmail("admin@test.com")).thenReturn(admin);
//
//        AdminDto result = adminService.loginAdmin("admin@test.com", "password");
//        assertNotNull(result);
//        assertEquals("admin@test.com", result.getEmail());
//        verify(adminRepository, times(1)).findByEmail("admin@test.com");
    }

//    @Test
//    void getUserDetails() {
//        List<Object> userDetails = Arrays.asList(new Object(), new Object());
//        when(adminRepository.getUserDetailsByEmail("admin@test.com")).thenReturn(userDetails);
//
//        List<Object> result = adminService.getUserDetails("admin@test.com");
//        assertEquals(2, result.size());
//        verify(adminRepository, times(1)).getUserDetailsByEmail("admin@test.com");
//    }

