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
        products.add(new Product(null,"Tea",15,"https://th.bing.com/th/id/OIP.fbyW5aeaY_JOIDVO7uNzlgHaFF?rs=1&pid=ImgDetMain"));
        products.add(new Product(null,"Coffee",15,"https://th.bing.com/th/id/OIP.HxFfBmqvKsktRmVJcbguBwHaE8?rs=1&pid=ImgDetMain"));
        products.add(new Product(null,"Sandwich",35,"https://images4.alphacoders.com/664/664192.jpg"));
        products.add(new Product(null,"Apple juice",30,"https://th.bing.com/th/id/OIP.a5vbXJVyEtZ6g44E_HgsSQHaFx?w=227&h=180&c=7&r=0&o=5&pid=1.7    "));
        products.add(new Product(null,"Carrot juice",40,"https://th.bing.com/th/id/OIP.UasOC2jpBZYuWDZq5MBfgAHaHS?w=180&h=180&c=7&r=0&o=5&pid=1.7"));




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
