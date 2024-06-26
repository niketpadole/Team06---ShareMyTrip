package com.axis.usermanagementservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherRegistrationRequest {

	private String firstName;
	private String lastName;
	@NotBlank(message = "Mobile cannot be blank")
	@Pattern(regexp = "^[7-9][0-9]{9}$", message = "Invalid mobile number")
	private long mobile;
	private java.sql.Date dateOfBirth;
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Invalid email format")
	private String email;

	private String drivingLicense;
//    @NotBlank(message = "Aadhar card number cannot be blank")
//    @Size(min = 12, max = 12, message = "Aadhar card number must be 12 digits")
//    @Pattern(regexp="^[0-9]{12}$", message = "Invalid Aadhar card number")
	private String aadharCard;
	private String miniBio;
	@NotBlank(message = "Password cannot be blank")
	private String password;
	private String vehicleModelName;
	// @NotBlank(message = "Driver's license number cannot be blank")
	// @Pattern(regexp="^[A-Za-z]{2}[0-9]{13}[0-9]{2}[0-9]{7}$", message = "Invalid
	// driver's license number")
	private String vehicleNo;

}
