package com.axis.usermanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.usermanagementservice.entity.Passenger;


@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    Passenger findByEmail(String email);

	Passenger findByAadharCard(String aadharCard);
}
