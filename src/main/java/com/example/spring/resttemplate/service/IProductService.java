package com.example.spring.resttemplate.service;

import com.example.spring.resttemplate.model.Product;

import java.util.List;

public interface IProductService {

    Product addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product updateProduct(Product product);
    void deleteProductById(Long id);

}
