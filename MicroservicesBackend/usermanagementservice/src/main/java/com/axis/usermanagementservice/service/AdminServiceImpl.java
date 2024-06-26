package com.axis.usermanagementservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.axis.usermanagementservice.dto.AdminDto;
import com.axis.usermanagementservice.dto.AuthRequest;
import com.axis.usermanagementservice.entity.Admin;
import com.axis.usermanagementservice.entity.Passenger;
import com.axis.usermanagementservice.entity.Publisher;
import com.axis.usermanagementservice.repository.AdminRepository;
import com.axis.usermanagementservice.repository.PassengerRepository;
import com.axis.usermanagementservice.repository.PublisherRepository;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PassengerRepository passengerRepository;
    
    @Autowired
    private PublisherRepository publisherRepository;
    
    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(int id) {
        return adminRepository.findById(id);
    }

    public Admin saveAdmin(Admin admin) {
    	//password hashing
        //admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        if(adminRepository.findByEmail(admin.getEmail())!=null)
        	return null;
        else
        {
        	AuthRequest authRequest=new AuthRequest(admin.getEmail(), admin.getPassword());
        	//rest call to authservice to register there also
        	ResponseEntity<Void> response = restTemplate.postForEntity("http://authserver/auth/register", authRequest, Void.class);

            if (response.getStatusCode().is2xxSuccessful()) {
            	admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            	return adminRepository.save(admin);
                
            } else {
                // Handle the case when registration in auth service fails
                throw new RuntimeException("Invalid request");
            }
        }
        
    }

    public Admin updateAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public void deleteAdmin(int id) {
        adminRepository.deleteById(id);
    }

    public AdminDto loginAdmin(String email, String password) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin == null || !passwordEncoder.matches(password, admin.getPassword())) {
            return null;
        }
        
        return modelMapper.map(admin,AdminDto.class);
    }

	@Override
	public List<Object> getUserDetails(String email) {
		List<Object> obj = new ArrayList<Object>();
		Publisher publisher=publisherRepository.findByEmail(email);
		Passenger passenger=passengerRepository.findByEmail(email);
		if(publisher==null&&passenger==null)
		{
			System.out.println("Inside if");
			return obj;
			
		}
		else {
			if(passenger!=null)
			obj.add(passenger);
			obj.add(publisher);
			System.out.println("Inside else");
			return obj;
		}
		
	}

}
