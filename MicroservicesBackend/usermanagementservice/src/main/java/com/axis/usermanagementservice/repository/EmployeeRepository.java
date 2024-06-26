package com.axis.usermanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.usermanagementservice.entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findByEmail(String email);
}
