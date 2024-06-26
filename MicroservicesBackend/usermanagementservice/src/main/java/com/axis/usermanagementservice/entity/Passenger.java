package com.axis.usermanagementservice.entity;

import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passenger_table")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private int passengerId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "mobile", nullable = false)
    private Long mobile;

    @Column(name = "date_of_birth", nullable = false)
    private java.sql.Date dateOfBirth;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "user_type")
    private String userType = "PASSENGER";

    @Column(name = "aadhar_card")
    private String aadharCard;

    @Column(name = "mini_bio")
    private String miniBio;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "timestamp")
    private Timestamp timestamp;

}
