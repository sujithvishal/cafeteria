package com.softwareag.cafeteria.repository;

import com.softwareag.cafeteria.model.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
//import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends ElasticsearchRepository<Employee,String> {
    List<Employee> findAll();
}
