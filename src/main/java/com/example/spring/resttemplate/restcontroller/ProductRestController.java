package com.example.spring.resttemplate.restcontroller;

import com.example.spring.resttemplate.model.Product;
import com.example.spring.resttemplate.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    private IProductService productService;

    @Autowired
    public ProductRestController(IProductService productService) {
        this.productService = productService;
    }


    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProduct(){
        return productService.getAllProducts();
    }


    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }


    @PutMapping("/products/{id}")
    public Product updateProduct(@RequestBody Product product , @PathVariable Long id){
        product.setId(id);
        return productService.updateProduct(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
    }


}
