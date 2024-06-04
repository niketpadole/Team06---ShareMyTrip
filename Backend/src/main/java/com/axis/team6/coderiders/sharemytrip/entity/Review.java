package com.axis.team6.coderiders.sharemytrip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "review_table")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Column(name = "publisher_rating", nullable = false)
    private int publisherRating;

    @Column(name = "publisher_review")
    private String publisherReview;

    @Column(name = "passenger_rating", nullable = false)
    private int passengerRating;

    @Column(name = "passenger_review")
    private String passengerReview;

    @Column(name = "timestamp", nullable = false)
    private java.sql.Timestamp timestamp;

}
