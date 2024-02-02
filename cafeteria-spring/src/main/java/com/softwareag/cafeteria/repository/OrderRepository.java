package com.softwareag.cafeteria.repository;

import com.softwareag.cafeteria.model.Employee;
import com.softwareag.cafeteria.model.Order;
import com.softwareag.cafeteria.model.OrderStatus;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends ElasticsearchRepository<Order,String> {

    List<Order> findByStatus(OrderStatus status);
    List<Order> findByEmployeeAndStatus(Employee employee, OrderStatus status);
    List<Order> findByEmployee(String employee);

    List<Order> findAll();
}
