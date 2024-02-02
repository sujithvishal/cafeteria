package com.softwareag.cafeteria.repository;

import com.softwareag.cafeteria.model.Employee;
import com.softwareag.cafeteria.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
//import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends ElasticsearchRepository<Product,String> {
    List<Product> findAll();
}
