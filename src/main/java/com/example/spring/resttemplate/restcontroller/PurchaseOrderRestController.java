package com.example.spring.resttemplate.restcontroller;

import com.example.spring.resttemplate.model.PurchaseOrder;
import com.example.spring.resttemplate.service.IPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PurchaseOrderRestController {

    private IPurchaseOrderService orderService;

    @Autowired
    public PurchaseOrderRestController(IPurchaseOrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/orders")
    public PurchaseOrder addOrder(@RequestBody PurchaseOrder purchaseOrder){
        return orderService.addOrder(purchaseOrder);
    }


    @GetMapping("/orders")
    public List<PurchaseOrder> getAllOrders(){
        return orderService.getAllOrders();
    }


    @GetMapping("/orders/{id}")
    public PurchaseOrder getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }


    @PutMapping("/orders/{id}")
    public PurchaseOrder updateOrder(@RequestBody PurchaseOrder purchaseOrder, @PathVariable Long id){
        purchaseOrder.setId(id);
        return orderService.updateOrder(purchaseOrder);
    }


    @DeleteMapping("/orders/{id}")
    public void deleteOrderById(@PathVariable Long id){
        orderService.deleteOrderById(id);
    }

    @PostMapping("/orders/{productId}/{quantity}")
    public PurchaseOrder order(@PathVariable("productId") Long productId ,@PathVariable("quantity") int quantity){
        return orderService.order(productId , quantity);
    }

}
