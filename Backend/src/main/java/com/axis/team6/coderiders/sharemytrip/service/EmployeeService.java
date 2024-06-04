package com.axis.team6.coderiders.sharemytrip.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.axis.team6.coderiders.sharemytrip.entity.Employee;

@Service
public interface EmployeeService {
	public List<Employee> getAllEmployees();

    public Employee getEmployeeById(int id);

    public Employee saveEmployee(Employee employee);

    public Employee updateEmployee(Employee employee);

    public void deleteEmployee(int id);
    
    public Employee loginEmployee(String email, String password);
}
