package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.models.PaymentDetails;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDetails,Long> {
    Optional<PaymentDetails> findByOrderId(String orderId);
}
