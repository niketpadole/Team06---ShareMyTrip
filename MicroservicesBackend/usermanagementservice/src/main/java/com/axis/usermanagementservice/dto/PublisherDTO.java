package com.axis.usermanagementservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherDTO {

	private int publisherId;

	private String firstName;

	private String lastName;

	private Long mobile;

	private java.sql.Date dateOfBirth;

	private String email;

	private String userType = "PUBLISHER";

	private String drivingLicense;

	private String aadharCard;

	private String miniBio;

	private String password;

	private String vehicleModelName;

	private String vehicleNo;

	private java.sql.Timestamp timestamp;

	private Float totalEarnings;
	private String token;

}
