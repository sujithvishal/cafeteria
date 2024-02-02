package com.softwareag.cafeteria.controller;


import com.softwareag.cafeteria.model.*;
import com.softwareag.cafeteria.model.request.OrderRequest;
import com.softwareag.cafeteria.model.request.OrderRequestItems;
import com.softwareag.cafeteria.repository.EmployeeRepository;
import com.softwareag.cafeteria.service.EmployeeService;
import com.softwareag.cafeteria.service.OrderService;
import com.softwareag.cafeteria.service.ProductService;
import com.softwareag.cafeteria.util.AdminUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AdminUtils adminUtils;

    @GetMapping("/orders")
    public List<Order> getAllOrders(HttpServletRequest req){
        adminUtils.checkAdmin((String) req.getAttribute("employeeRole"));
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/pending")
    public List<Order> getPendingOrders(HttpServletRequest req){
        adminUtils.checkAdmin((String) req.getAttribute("employeeRole"));
        return orderService.getPendingOrders();
    }

    @GetMapping("/orders/employee")
    public List<Order> getOrdersByEmployee(@RequestParam("id") String id){
//        System.out.println(id);
        Optional<Employee> employee= Optional.ofNullable(employeeService.getEmployeeById(id));
        if(!employee.isPresent())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found");
        return orderService.getOrdersByEmployee(employee.get().getId());

    }
    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable  String id){

        return orderService.getOrderById(id);


    }

    @PostMapping("/orders")
    public Order placeOrder(@RequestBody OrderRequest orderRequest){
        Order order =new Order();
//        System.out.println(orderRequest.toString());
        BeanUtils.copyProperties(orderRequest,order);
        order.setDate(LocalDate.now());
        order.setEmployee(orderRequest.getEmployeeId());
        order.setItems(new ArrayList<>());
        order.setStatus(OrderStatus.PENDING);
        for(OrderRequestItems ori:orderRequest.getOrderRequestItems()){
            Product product= productService.getProductById(ori.getProduct().getId());
            order.getItems().add(new OrderItems(product,ori.getCount()));
        }
//        System.out.println(order.toString());

        return orderService.placeOrder(order);

    }

    @PutMapping("/orders")
    public Order updateOrderStatus(@RequestParam("id") String id,@RequestParam("status") String status,HttpServletRequest req){
        adminUtils.checkAdmin((String) req.getAttribute("employeeRole"));
        OrderStatus st;
        if(status.equalsIgnoreCase("prepared")){
            st=OrderStatus.PREPARED;
        }else{
            st=OrderStatus.CLEARED;
        }

        return orderService.updateOrderStatus(id,st);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable  String id){

        orderService.deleteOrderById(id);
        return ResponseEntity.ok("Order deleted Successfully");

    }



}
