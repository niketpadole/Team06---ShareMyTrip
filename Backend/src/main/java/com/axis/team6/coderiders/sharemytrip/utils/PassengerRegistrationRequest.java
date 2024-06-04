package com.axis.team6.coderiders.sharemytrip.utils;

import lombok.Data;

@Data
public class PassengerRegistrationRequest {

    private String firstName;
    private String lastName;
    private long mobile;
    private java.sql.Date dateOfBirth;
    private String email;
    private String aadharCard;
    private String miniBio;
    private String password;

}
