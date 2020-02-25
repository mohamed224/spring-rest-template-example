package com.example.spring.resttemplate.service.impl;

import com.example.spring.resttemplate.dao.PurchaseOrderRepository;
import com.example.spring.resttemplate.model.Product;
import com.example.spring.resttemplate.model.PurchaseOrder;
import com.example.spring.resttemplate.service.IPurchaseOrderService;
import com.example.spring.resttemplate.util.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class PurchaseOrderService implements IPurchaseOrderService {

    private PurchaseOrderRepository purchaseOrderRepository;
    private RestTemplate restTemplate;

    @Autowired
    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository , RestTemplate restTemplate) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public PurchaseOrder addOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public List<PurchaseOrder> getAllOrders() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public PurchaseOrder getOrderById(Long id) {
        return purchaseOrderRepository.findById(id).get();
    }

    @Override
    public PurchaseOrder updateOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.saveAndFlush(purchaseOrder);
    }

    @Override
    public void deleteOrderById(Long id) {
        purchaseOrderRepository.deleteById(id);
    }

    @Override
    public PurchaseOrder order(Long productId, int quantity) {

        Product orderedProduct = restTemplate.getForObject(Uri.PRODUCT_URI+productId, Product.class);

        if(orderedProduct.getQtyStock()<quantity){
            throw new RuntimeException("insufficient quantity of product");
        }
        else {
            PurchaseOrder newPurchaseOrder = new PurchaseOrder(null,productId,new Date(),quantity,false);
            PurchaseOrder saveTheOrder = purchaseOrderRepository.save(newPurchaseOrder);
            if(saveTheOrder == null){
                throw new RuntimeException("unable to place order");
            }
            else{
                return saveTheOrder;
            }
        }

    }

}
