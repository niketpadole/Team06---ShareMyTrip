package com.axis.team6.coderiders.sharemytrip.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.team6.coderiders.sharemytrip.entity.Employee;
import com.axis.team6.coderiders.sharemytrip.exception.EmployeeNotFoundException;
import com.axis.team6.coderiders.sharemytrip.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	    @Autowired
	    private EmployeeRepository employeeRepository;

	    public List<Employee> getAllEmployees() {
	        return employeeRepository.findAll();
	    }

	    public Employee getEmployeeById(int id) {
	        return employeeRepository.findById(id).get();
	    }

	    public Employee saveEmployee(Employee employee) {
	        return employeeRepository.save(employee);
	    }

	    public Employee updateEmployee(Employee employee) {
	        return employeeRepository.save(employee);
	    }

	    public void deleteEmployee(int id) {
	        employeeRepository.deleteById(id);
	    }
	    
	    public Employee loginEmployee(String email, String password) {
	        Employee employee = employeeRepository.findByEmailAndPassword(email, password);
	        if (employee == null) {
	            throw new EmployeeNotFoundException("Invalid email or password");
	        }
	        return employee;
	    }

}
