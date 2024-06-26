package com.axis.usermanagementservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.usermanagementservice.entity.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Admin findByEmail(String email);
}
