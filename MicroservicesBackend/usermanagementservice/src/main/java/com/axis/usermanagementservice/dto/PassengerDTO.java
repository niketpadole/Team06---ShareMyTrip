package com.axis.usermanagementservice.dto;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDTO {
	private int passengerId;

	private String firstName;

	private String lastName;

	private Long mobile;

	private java.sql.Date dateOfBirth;

	private String email;

	private String userType = "PASSENGER";

	private String aadharCard;

	private String miniBio;

	private String password;

	private Timestamp timestamp;
	private String token;
}
