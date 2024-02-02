package com.softwareag.cafeteria.service;


import com.softwareag.cafeteria.model.Employee;
import com.softwareag.cafeteria.model.Order;
import com.softwareag.cafeteria.model.OrderStatus;
import com.softwareag.cafeteria.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private EmployeeService employeeService;
    public List<Order> getAllOrders(){

        return (List<Order>) orderRepository.findAll();

    }

    public List<Order> getPendingOrders(){
        List<Order> orders= orderRepository.findByStatus(OrderStatus.PENDING);
        Collections.sort(orders, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return o1.getDate().isAfter(o2.getDate()) ? 1 : -1;
            }
        });
        return orders;
        
    }

    public Order getOrderById(String id){
        Optional<Order> order= orderRepository.findById(id);
        if(!order.isPresent())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Order Not Found");
        return order.get();
    }

    public List<Order> getOrdersByEmployee(String employee){
        System.out.println(employee);
        List<Order> orders= orderRepository.findByEmployee(employee);
        System.out.println(orders);
        Collections.sort(orders, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                if(o1.getDate().equals(o2.getDate())){
                    if(o1.getStatus().equals(OrderStatus.PENDING))return -1;
                }

                return o1.getDate().isAfter(o2.getDate()) ? -1 : 1;
            }
        });
        return orders;

    }

    public Order placeOrder(Order order){

        employeeService.updateBalance(order.getEmployee(),order.getTotal());

        return orderRepository.save(order);
    }

    public Order updateOrderStatus(String id,OrderStatus status){
        //logic
        Optional<Order> order = Optional.ofNullable(getOrderById(id));
        if(!order.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Order Not found");
        Order currentOrder=order.get();
        currentOrder.setStatus(status);
        return orderRepository.save(currentOrder);
    }

    public void deleteOrderById(String id){
        orderRepository.deleteById(id);
    }



}
