package com.axis.usermanagementservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.axis.usermanagementservice.dto.AdminDto;
import com.axis.usermanagementservice.dto.AuthRequest;
import com.axis.usermanagementservice.dto.LoginRequest;
import com.axis.usermanagementservice.dto.PassengerDTO;
import com.axis.usermanagementservice.dto.PublisherDTO;
import com.axis.usermanagementservice.dto.RegisterRequest;
import com.axis.usermanagementservice.entity.Admin;
import com.axis.usermanagementservice.exception.AdminNotFoundException;
import com.axis.usermanagementservice.service.AdminService;
import com.axis.usermanagementservice.service.PassengerService;
import com.axis.usermanagementservice.service.PublisherService;

@RestController
@RequestMapping("/user/admins")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PublisherService publisherService;
    
    @Autowired
    private PassengerService passengerService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;
    
    private static final String AUTH_SERVICE_URL = "http://authserver/auth/register";
    private static final String AUTH_SERVICE="http://authserver";
    @GetMapping
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        List<AdminDto> adminDtos = admins.stream()
                .map(admin -> modelMapper.map(admin, AdminDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(adminDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDto> getAdminById(@PathVariable int id) {
        Admin admin = adminService.getAdminById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found"));
        AdminDto adminDto = modelMapper.map(admin, AdminDto.class);
        return ResponseEntity.ok(adminDto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createAdmin(@RequestBody RegisterRequest registerRequest) {
        Admin admin = modelMapper.map(registerRequest, Admin.class);
        Admin savedAdmin = adminService.saveAdmin(admin);
        if(savedAdmin==null)
        	return new ResponseEntity<>("Admin Exists..Please Login!!!",HttpStatus.CONFLICT);
        return new ResponseEntity<>("Admin added!!!",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> updateAdmin(@PathVariable int id, @RequestBody RegisterRequest registerRequest) {
        Admin existingAdmin = adminService.getAdminById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found"));

        existingAdmin.setEmail(registerRequest.getEmail());
        existingAdmin.setPassword(registerRequest.getPassword());
        existingAdmin.setAdminFullName(registerRequest.getAdminFullName());

        Admin updatedAdmin = adminService.updateAdmin(existingAdmin);
        AdminDto adminDto = modelMapper.map(updatedAdmin, AdminDto.class);
        return ResponseEntity.ok(adminDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable int id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AdminDto> loginAdmin(@RequestBody LoginRequest loginRequest) {
        AdminDto admin = adminService.loginAdmin(loginRequest.getEmail(), loginRequest.getPassword());
        if (admin != null) {
			AuthRequest authRequest = new AuthRequest(loginRequest.getEmail(), loginRequest.getPassword());
			ResponseEntity<String> authResponse = restTemplate.postForEntity(AUTH_SERVICE+"/auth/token",authRequest, String.class);
			// System.out.println("Authresponse.getbody======="+authResponse.getBody());
			if (authResponse.getStatusCode() == HttpStatus.OK && authResponse.getBody() != null) {
				admin.setToken(authResponse.getBody());
				return ResponseEntity.ok(admin);
			} else {
				return ResponseEntity.status(authResponse.getStatusCode()).body(null);
			}
		} else {
			return ResponseEntity.status(401).body(null);
		}
    }
    
    
    //View publishers
    @GetMapping("/publishers")
    public ResponseEntity<List<PublisherDTO>> getAllPublisher(){
    	List<PublisherDTO> publishers=publisherService.getAllPublishers();
    	return ResponseEntity.ok(publishers);
    }
    
    //View passengers
    @GetMapping("/passengers")
    public ResponseEntity<List<PassengerDTO>> getAllPassengers(){
    	List<PassengerDTO> passengers=passengerService.getAllPassengers();
    	return ResponseEntity.ok(passengers);
    }
    
    //search user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserDetails(@PathVariable String email){
    	List<Object> user=adminService.getUserDetails(email);
    	user.stream().forEach(System.out::println);
    	return ResponseEntity.ok(user);
    	
    }
    
    //view publisher by Id
    public ResponseEntity<PublisherDTO> getPublisher(@PathVariable Integer id){
    	PublisherDTO p=publisherService.getPublisherById(id);
    	return ResponseEntity.ok(p);
    }
    
    //view passenger by Id
    
    
    
}
