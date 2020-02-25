package com.example.spring.resttemplate;

import com.example.spring.resttemplate.dao.PaymentRepository;
import com.example.spring.resttemplate.dao.ProductRepository;
import com.example.spring.resttemplate.dao.PurchaseOrderRepository;
import com.example.spring.resttemplate.model.Payment;
import com.example.spring.resttemplate.model.Product;
import com.example.spring.resttemplate.model.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootApplication
public class SpringRestTemplateExampleApplication implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PurchaseOrderRepository orderRepository;
    public static void main(String[] args) {
        SpringApplication.run(SpringRestTemplateExampleApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product(1L,"HP 230",15,2500.4));
        productRepository.save(new Product(1L,"SONY VAIO",10,2230.4));

        paymentRepository.save(new Payment(1L,1L,1200.8,23000L));

        orderRepository.save(new PurchaseOrder(1L , 1L , new Date(),10,false));
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
