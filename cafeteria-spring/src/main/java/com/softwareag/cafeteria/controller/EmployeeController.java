package com.softwareag.cafeteria.controller;

import com.softwareag.cafeteria.model.request.LoginRequest;
import com.softwareag.cafeteria.service.EmployeeService;
import com.softwareag.cafeteria.util.AdminUtils;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.softwareag.cafeteria.model.Employee;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AdminUtils adminUtils;

    @PostConstruct
    public void init(){
        List<Employee> employees=new ArrayList<>();
        employees.add(new Employee("5001","Sujith","1234",200,"USER"));
        employees.add(new Employee("5002","Pragadeesh","1234",200,"USER"));
        employees.add(new Employee("5000","Admin","admin",200,"ADMIN"));

        employeeService.addAllEmployee(employees);
    }



    @GetMapping("/employee")
    public List<Employee> getAllEmployee(HttpServletRequest req){
//        adminUtils.checkAdmin((String) req.getAttribute("employeeRole"));

        return employeeService.getAllEmployee();
    }

    @GetMapping("/employee/{id}")
    public Employee findEmployee(@PathVariable String id,HttpServletRequest req){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employee/login")
    public ResponseEntity<Object> authenticateEmployee(@RequestBody LoginRequest loginRequest){
       System.out.println(loginRequest);
        String token= employeeService.authenticateEmployee(loginRequest.getId(),loginRequest.getPassword());

        if(token==null)throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        Employee employee=employeeService.getEmployeeById(loginRequest.getId());
        HashMap<String,String> hm =new HashMap<>();
        hm.put("token",token);
        hm.put("employeeId",employee.getId());
        hm.put("employeeName", employee.getName());
        hm.put("balance", String.valueOf(employee.getBalance()));
        hm.put("role", String.valueOf(employee.getRole()));

        return ResponseEntity.ok(hm);

    }
    @PostMapping("/employee/admin/login")
    public ResponseEntity<Object> authenticateEmployeeAdmin(@RequestBody LoginRequest loginRequest){
//        System.out.println(loginRequest);
        String token= employeeService.authenticateEmployee(loginRequest.getId(),loginRequest.getPassword());

        if(token==null)throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        Employee employee=employeeService.getEmployeeById(loginRequest.getId());
        if(!employee.getRole().contains("ADMIN"))throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Not an admin");
        HashMap<String,String> hm =new HashMap<>();
        hm.put("token",token);
        hm.put("employeeId",employee.getId());
        hm.put("employeeName", employee.getName());
        hm.put("balance", String.valueOf(employee.getBalance()));
        hm.put("role", String.valueOf(employee.getRole()));

        return ResponseEntity.ok(hm);

    }



    @PostMapping("/employee/add")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee,HttpServletRequest req){
//        adminUtils.checkAdmin((String) req.getAttribute("employeeRole"));
       Employee savedEmployee= employeeService.createEmployee(employee);


        return ResponseEntity.ok(savedEmployee);

    }

    @PostMapping("/employee/add-all")
    public ResponseEntity<Object> addAllEmployee(@RequestBody List<Employee> employees,HttpServletRequest req){
//        adminUtils.checkAdmin((String) req.getAttribute("employeeRole"));
        List<Employee> savedEmployee= employeeService.addAllEmployee(employees);

        return ResponseEntity.ok(savedEmployee);

    }


}
