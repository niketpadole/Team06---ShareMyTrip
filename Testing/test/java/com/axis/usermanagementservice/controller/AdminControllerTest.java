package com.axis.usermanagementservice.controller;

import com.axis.usermanagementservice.dto.*;
import com.axis.usermanagementservice.entity.Admin;
import com.axis.usermanagementservice.exception.AdminNotFoundException;
import com.axis.usermanagementservice.service.AdminService;
import com.axis.usermanagementservice.service.PassengerService;
import com.axis.usermanagementservice.service.PublisherService;
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
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminService adminService;

    @Mock
    private PublisherService publisherService;

    @Mock
    private PassengerService passengerService;

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
        Admin admin = new Admin();
        AdminDto adminDto = new AdminDto();
        List<Admin> adminList = Arrays.asList(admin);
        when(adminService.getAllAdmins()).thenReturn(adminList);
        when(modelMapper.map(any(Admin.class), eq(AdminDto.class))).thenReturn(adminDto);

        ResponseEntity<List<AdminDto>> response = adminController.getAllAdmins();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(adminService, times(1)).getAllAdmins();
    }

    @Test
    void getAdminById() {
        Admin admin = new Admin();
        AdminDto adminDto = new AdminDto();
        when(adminService.getAdminById(anyInt())).thenReturn(Optional.of(admin));
        when(modelMapper.map(any(Admin.class), eq(AdminDto.class))).thenReturn(adminDto);

        ResponseEntity<AdminDto> response = adminController.getAdminById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(adminService, times(1)).getAdminById(1);
    }

    @Test
    void createAdmin() {
        Admin admin = new Admin();
        RegisterRequest registerRequest = new RegisterRequest();
        when(modelMapper.map(any(RegisterRequest.class), eq(Admin.class))).thenReturn(admin);
        when(adminService.saveAdmin(any(Admin.class))).thenReturn(admin);

        ResponseEntity<?> response = adminController.createAdmin(registerRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(adminService, times(1)).saveAdmin(any(Admin.class));
    }

    @Test
    void updateAdmin() {
        Admin admin = new Admin();
        RegisterRequest registerRequest = new RegisterRequest();
        when(adminService.getAdminById(anyInt())).thenReturn(Optional.of(admin));
        when(adminService.updateAdmin(any(Admin.class))).thenReturn(admin);
        when(modelMapper.map(any(Admin.class), eq(AdminDto.class))).thenReturn(new AdminDto());

        ResponseEntity<AdminDto> response = adminController.updateAdmin(1, registerRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(adminService, times(1)).getAdminById(1);
        verify(adminService, times(1)).updateAdmin(any(Admin.class));
    }

    @Test
    void deleteAdmin() {
        doNothing().when(adminService).deleteAdmin(anyInt());

        ResponseEntity<Void> response = adminController.deleteAdmin(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(adminService, times(1)).deleteAdmin(1);
    }

    @Test
    void loginAdmin() {
        AdminDto adminDto = new AdminDto();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@test.com");
        loginRequest.setPassword("password");

        when(adminService.loginAdmin(anyString(), anyString())).thenReturn(adminDto);
        when(restTemplate.postForEntity(anyString(), any(), eq(String.class))).thenReturn(new ResponseEntity<>("token", HttpStatus.OK));

        ResponseEntity<AdminDto> response = adminController.loginAdmin(loginRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("token", response.getBody().getToken());
        verify(adminService, times(1)).loginAdmin(anyString(), anyString());
    }

    @Test
    void getAllPublisher() {
        PublisherDTO publisherDTO = new PublisherDTO();
        List<PublisherDTO> publisherList = Arrays.asList(publisherDTO);
        when(publisherService.getAllPublishers()).thenReturn(publisherList);

        ResponseEntity<List<PublisherDTO>> response = adminController.getAllPublisher();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(publisherService, times(1)).getAllPublishers();
    }

    @Test
    void getAllPassengers() {
        PassengerDTO passengerDTO = new PassengerDTO();
        List<PassengerDTO> passengerList = Arrays.asList(passengerDTO);
        when(passengerService.getAllPassengers()).thenReturn(passengerList);

        ResponseEntity<List<PassengerDTO>> response = adminController.getAllPassengers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(passengerService, times(1)).getAllPassengers();
    }

    @Test
    void getUserDetails() {
        Object user = new Object();
        List<Object> userList = Arrays.asList(user);
        when(adminService.getUserDetails(anyString())).thenReturn(userList);

        ResponseEntity<?> response = adminController.getUserDetails("test@test.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(adminService, times(1)).getUserDetails(anyString());
    }

    @Test
    void getPublisher() {
        PublisherDTO publisherDTO = new PublisherDTO();
        when(publisherService.getPublisherById(anyInt())).thenReturn(publisherDTO);

        ResponseEntity<PublisherDTO> response = adminController.getPublisher(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(publisherService, times(1)).getPublisherById(1);
    }
}
