package com.axis.team6.coderiders.sharemytrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.team6.coderiders.sharemytrip.entity.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    Passenger findByEmailAndPassword(String email, String password);
}
