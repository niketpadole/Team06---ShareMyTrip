package com.axis.team6.coderiders.sharemytrip.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class PublisherRegistrationRequest {

    private String firstName;
    private String lastName;
    private long mobile;
    private java.sql.Date dateOfBirth;
    private String email;
    private String drivingLicense;
    private String aadharCard;
    private String miniBio;
    private String password;
    private String vehicleModelName;
    private String vehicleNo;

}
