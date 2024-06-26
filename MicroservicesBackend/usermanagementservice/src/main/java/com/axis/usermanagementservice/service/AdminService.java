package com.axis.usermanagementservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.axis.usermanagementservice.dto.AdminDto;
import com.axis.usermanagementservice.entity.Admin;

@Service
public interface AdminService {
    List<Admin> getAllAdmins();
    Optional<Admin> getAdminById(int id);
    Admin saveAdmin(Admin admin);
    Admin updateAdmin(Admin admin);
    void deleteAdmin(int id);
    AdminDto loginAdmin(String email, String password);
	List<Object> getUserDetails(String email);
}
