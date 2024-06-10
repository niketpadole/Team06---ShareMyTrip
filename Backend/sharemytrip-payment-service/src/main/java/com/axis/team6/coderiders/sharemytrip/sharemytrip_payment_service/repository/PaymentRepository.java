package com.axis.team6.coderiders.sharemytrip.sharemytrip_payment_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.team6.coderiders.sharemytrip.sharemytrip_payment_service.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}