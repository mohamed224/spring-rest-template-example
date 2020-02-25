package com.example.spring.resttemplate.service;

import com.example.spring.resttemplate.model.PurchaseOrder;

import java.util.List;

public interface IPurchaseOrderService {

    PurchaseOrder addOrder(PurchaseOrder purchaseOrder);
    List<PurchaseOrder> getAllOrders();
    PurchaseOrder getOrderById(Long id);
    PurchaseOrder updateOrder(PurchaseOrder purchaseOrder);
    void deleteOrderById(Long id);
    PurchaseOrder order(Long productId , int quantity);

}
