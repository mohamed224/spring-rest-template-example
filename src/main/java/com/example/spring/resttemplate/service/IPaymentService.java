package com.example.spring.resttemplate.service;

import com.example.spring.resttemplate.model.Payment;

import java.util.List;

public interface IPaymentService {

    Payment addPayment(Payment product);
    List<Payment> getAllPayments();
    Payment getPaymentById(Long id);
    Payment updatePayment(Payment product);
    Payment payAnOrder(Long orderId , Double amount);
}
