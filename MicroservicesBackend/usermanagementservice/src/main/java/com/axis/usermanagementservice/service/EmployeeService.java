package com.axis.usermanagementservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.axis.usermanagementservice.entity.Employee;

@Service
public interface EmployeeService {
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(int id);
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(int id);
    Employee loginEmployee(String email, String password);
}
