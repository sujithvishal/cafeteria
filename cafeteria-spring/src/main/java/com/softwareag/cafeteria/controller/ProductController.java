package com.softwareag.cafeteria.controller;


import com.softwareag.cafeteria.model.Employee;
import com.softwareag.cafeteria.model.Product;
import com.softwareag.cafeteria.service.ProductService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostConstruct
    public void init(){
        List<Product> products=new ArrayList<>();



        productService.addAllProducts(products);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable String id){
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }

    @PostMapping("/products/add-all")
    public List<Product> addAllProducts(@RequestBody List<Product> products){
        return productService.addAllProducts(products);
    }
    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id){
        productService.deleteProductById(id);
        return new ResponseEntity<String>("product deleted successfully", HttpStatus.OK);

    }


}
