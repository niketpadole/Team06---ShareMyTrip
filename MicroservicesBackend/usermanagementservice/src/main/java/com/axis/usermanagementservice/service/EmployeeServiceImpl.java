package com.axis.usermanagementservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.axis.usermanagementservice.entity.Employee;
import com.axis.usermanagementservice.exception.EmployeeNotFoundException;
import com.axis.usermanagementservice.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
    	//password hashing
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    public Employee loginEmployee(String email, String password) {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null || !passwordEncoder.matches(password, employee.getPassword())) {
            throw new EmployeeNotFoundException("Invalid email or password");
        }
        return employee;
    }
}
