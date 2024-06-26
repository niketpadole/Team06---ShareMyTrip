package com.axis.usermanagementservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PassengerRegistrationRequest {

    private String firstName;
    private String lastName;
    @NotBlank(message = "Mobile cannot be blank")
    @Pattern(regexp="^[7-9][0-9]{9}$", message = "Invalid mobile number")
    private long mobile;
    private java.sql.Date dateOfBirth;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Aadhar card number cannot be blank")
//    @Size(min = 12, max = 12, message = "Aadhar card number must be 12 digits")
//    @Pattern(regexp="^[0-9]{12}$", message = "Invalid Aadhar card number")
    private String aadharCard;
    private String miniBio;
    @NotBlank(message = "Password cannot be blank")
    private String password;

}
