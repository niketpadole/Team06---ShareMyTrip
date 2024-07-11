package com.axis.usermanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.usermanagementservice.dto.PublisherDTO;
import com.axis.usermanagementservice.entity.Publisher;



@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

    Publisher findByEmail(String email);

	Publisher findByDrivingLicense(String drivingLicense);
	Publisher findByAadharCard(String aadharCard);

	
}
