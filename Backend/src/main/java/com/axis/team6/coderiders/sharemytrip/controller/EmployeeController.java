package com.axis.team6.coderiders.sharemytrip.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axis.team6.coderiders.sharemytrip.entity.Employee;
import com.axis.team6.coderiders.sharemytrip.service.EmployeeService;
import com.axis.team6.coderiders.sharemytrip.utils.LoginRequest;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeService.getEmployeeById(id);
        employee.setEmail(employeeDetails.getEmail());
        employee.setPassword(employeeDetails.getPassword());
        employee.setEmployeeFullName(employeeDetails.getEmployeeFullName());
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }
    
    @PostMapping("/login")
    public Employee loginEmployee(@RequestBody LoginRequest loginRequest) {
        return employeeService.loginEmployee(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
