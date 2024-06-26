package com.axis.usermanagementservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axis.usermanagementservice.dto.EmployeeDto;
import com.axis.usermanagementservice.dto.LoginRequest;
import com.axis.usermanagementservice.dto.RegisterRequest;
import com.axis.usermanagementservice.entity.Employee;
import com.axis.usermanagementservice.exception.EmployeeNotFoundException;
import com.axis.usermanagementservice.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeDto> employeeDtos = employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return ResponseEntity.ok(employeeDto);
    }

    @PostMapping("/register")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody RegisterRequest registerRequest) {
        Employee employee = modelMapper.map(registerRequest, Employee.class);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        EmployeeDto employeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);
        return ResponseEntity.ok(employeeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable int id, @RequestBody RegisterRequest registerRequest) {
        Employee existingEmployee = employeeService.getEmployeeById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        existingEmployee.setEmail(registerRequest.getEmail());
        existingEmployee.setPassword(registerRequest.getPassword());
        existingEmployee.setEmployeeFullName(registerRequest.getEmployeeFullName());

        Employee updatedEmployee = employeeService.updateEmployee(existingEmployee);
        EmployeeDto employeeDto = modelMapper.map(updatedEmployee, EmployeeDto.class);
        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<EmployeeDto> loginEmployee(@RequestBody LoginRequest loginRequest) {
        Employee employee = employeeService.loginEmployee(loginRequest.getEmail(), loginRequest.getPassword());
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return ResponseEntity.ok(employeeDto);
    }
}
