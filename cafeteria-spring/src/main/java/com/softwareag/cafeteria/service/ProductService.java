package com.softwareag.cafeteria.service;


import com.softwareag.cafeteria.model.Product;
import com.softwareag.cafeteria.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return (List<Product>) productRepository.findAll();
    }
    public Product getProductById(String id){
        Optional<Product> product= productRepository.findById(id);
        if(!product.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found");

        return product.get();
    }

    public Product addNewProduct(Product product){

        return productRepository.save(product);

    }

    public Product updateProduct(Product product){
        String id=product.getId();
        Optional<Product> prod=productRepository.findById(id);
        if(!prod.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found");
        return productRepository.save(product);

    }

    public void deleteProductById(String id){
        productRepository.deleteById(id);
    }

    public List<Product> addAllProducts(List<Product> products) {
        return (List<Product>) productRepository.saveAll(products);
    }
}
