package com.example.spring.resttemplate.dao;

import com.example.spring.resttemplate.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment , Long> {
    Payment findByOrderId(Long orderId);
}
