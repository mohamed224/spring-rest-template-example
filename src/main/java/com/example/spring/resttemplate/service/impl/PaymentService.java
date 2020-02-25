package com.example.spring.resttemplate.service.impl;

import com.example.spring.resttemplate.dao.PaymentRepository;
import com.example.spring.resttemplate.model.Payment;
import com.example.spring.resttemplate.model.PurchaseOrder;
import com.example.spring.resttemplate.service.IPaymentService;
import com.example.spring.resttemplate.util.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PaymentService implements IPaymentService {

    private PaymentRepository paymentRepository;
    private RestTemplate restTemplate;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, RestTemplate restTemplate) {
        this.paymentRepository = paymentRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).get();
    }

    @Override
    public Payment updatePayment(Payment payment) {
        return paymentRepository.saveAndFlush(payment);
    }

    @Override
    public Payment payAnOrder(Long orderId , Double amount) {
        PurchaseOrder orderToPay = restTemplate.getForObject(Uri.PURCHASE_ORDER_URI+orderId , PurchaseOrder.class);
        Payment existingPayment = paymentRepository.findByOrderId(orderId);
        if(existingPayment != null){
            throw new RuntimeException("This order is already paid");
        }
        Payment newPayment = new Payment(null,orderId,amount, ThreadLocalRandom.current().nextLong(1000000000000000L,9000000000000000L));
        Payment saveThePayment = paymentRepository.save(newPayment);
        if(saveThePayment == null){
            throw new RuntimeException("Impossible to pay for this order. try later.");
        }
        else{
            orderToPay.setPaidOrder(true);
            restTemplate.put(Uri.PURCHASE_ORDER_URI+orderId,orderToPay);
            return saveThePayment;
        }
    }
}
