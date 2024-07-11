package com.axis.usermanagementservice.service;

import com.axis.usermanagementservice.dto.AdminDto;
import com.axis.usermanagementservice.dto.AuthRequest;
import com.axis.usermanagementservice.entity.Admin;
import com.axis.usermanagementservice.entity.Passenger;
import com.axis.usermanagementservice.entity.Publisher;
import com.axis.usermanagementservice.repository.AdminRepository;
import com.axis.usermanagementservice.repository.PassengerRepository;
import com.axis.usermanagementservice.repository.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private PublisherRepository publisherRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAdmins() {
        List<Admin> admins = Arrays.asList(new Admin());
        when(adminRepository.findAll()).thenReturn(admins);

        List<Admin> result = adminService.getAllAdmins();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(adminRepository, times(1)).findAll();
    }

    @Test
    void getAdminById() {
        Admin admin = new Admin();
        when(adminRepository.findById(anyInt())).thenReturn(Optional.of(admin));

        Optional<Admin> result = adminService.getAdminById(1);

        assertTrue(result.isPresent());
        assertEquals(admin, result.get());
        verify(adminRepository, times(1)).findById(1);
    }

    @Test
    void saveAdmin() {
        Admin admin = new Admin();
        admin.setEmail("admin@example.com");
        admin.setPassword("password");
        when(adminRepository.findByEmail(anyString())).thenReturn(null);
        when(restTemplate.postForEntity(anyString(), any(AuthRequest.class), eq(Void.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));
        when(adminRepository.save(any(Admin.class))).thenReturn(admin);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        Admin result = adminService.saveAdmin(admin);

        assertNotNull(result);
        assertEquals("encodedPassword", result.getPassword());
        verify(adminRepository, times(1)).findByEmail(admin.getEmail());
        verify(restTemplate, times(1)).postForEntity(anyString(), any(AuthRequest.class), eq(Void.class));
        verify(adminRepository, times(1)).save(any(Admin.class));
    }

//    @Test
//    void updateAdmin() {
//        Admin admin = new Admin();
//        when(adminRepository.save(any(Admin.class))).thenReturn(admin);
//        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
//
//        Admin result = adminService.updateAdmin(admin);
//
//        assertNotNull(result);
//        assertEquals("encodedPassword", result.getPassword());
//        verify(adminRepository, times(1)).save(admin);
//    }

    @Test
    void deleteAdmin() {
        doNothing().when(adminRepository).deleteById(anyInt());

        adminService.deleteAdmin(1);

        verify(adminRepository, times(1)).deleteById(1);
    }

    @Test
    void loginAdmin() {
        Admin admin = new Admin();
        admin.setEmail("admin@example.com");
        admin.setPassword("encodedPassword");
        when(adminRepository.findByEmail(anyString())).thenReturn(admin);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        AdminDto adminDto = new AdminDto();
        when(modelMapper.map(any(Admin.class), eq(AdminDto.class))).thenReturn(adminDto);

        AdminDto result = adminService.loginAdmin("admin@example.com", "password");

        assertNotNull(result);
        verify(adminRepository, times(1)).findByEmail("admin@example.com");
        verify(passwordEncoder, times(1)).matches("password", "encodedPassword");
    }

    @Test
    void getUserDetails() {
        Publisher publisher = new Publisher();
        publisher.setEmail("user@example.com");
        Passenger passenger = new Passenger();
        passenger.setEmail("user@example.com");
        when(publisherRepository.findByEmail(anyString())).thenReturn(publisher);
        when(passengerRepository.findByEmail(anyString())).thenReturn(passenger);

        List<Object> result = adminService.getUserDetails("user@example.com");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(publisher));
        assertTrue(result.contains(passenger));
        verify(publisherRepository, times(1)).findByEmail("user@example.com");
        verify(passengerRepository, times(1)).findByEmail("user@example.com");
    }
}
