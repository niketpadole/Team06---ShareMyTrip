package com.axis.usermanagementservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publisher_table")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private int publisherId;

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
    private String userType = "PUBLISHER";

    @Column(name = "driving_license")
    private String drivingLicense;

    @Column(name = "aadhar_card")
    private String aadharCard;

    @Column(name = "mini_bio")
    private String miniBio;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "vehicle_model_name", nullable = false)
    private String vehicleModelName;

    @Column(name = "vehicle_no", nullable = false)
    private String vehicleNo;

    @Column(name = "timestamp", nullable = false)
    private java.sql.Timestamp timestamp;
    
    @Column(name="total_earnings")//,columnDefinition = "FLOAT DEFAULT 0"
    private Float totalEarnings=0f;

}

