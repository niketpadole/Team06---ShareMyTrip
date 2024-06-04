package com.axis.team6.coderiders.sharemytrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.team6.coderiders.sharemytrip.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findByEmailAndPassword(String email, String password);
}
