package com.softwareag.cafeteria.service;


import com.softwareag.cafeteria.model.Employee;
import com.softwareag.cafeteria.repository.EmployeeRepository;
//import com.softwareag.cafeteria.util.JwtUtils;
//import io.jsonwebtoken.Jwt;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

//    @Autowired
//    private JwtUtils jwtUtils;

    public Employee getEmployeeById(String id){

        Optional<Employee> employee= employeeRepository.findById(id);
        return employee.orElse(null);
    }

    public Employee createEmployee(Employee employee){
        //        System.out.println(savedEmployee);
        return employeeRepository.save(employee);
    }

    public String authenticateEmployee(String id,String password){
        Employee employee=getEmployeeById(id);
        if(employee==null)throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND,"User not found");;
        if(!employee.getPassword().equals(password)){
             throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND,"Password incorrect ");

        };
//        return jwtUtils.generateJwt(employee);
        return "token";

    }
    public void updateBalance(String employeeId,int amount){

       Optional<Employee> employee= employeeRepository.findById(employeeId);
        if(employee.isEmpty())throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND,"User not found");;
       Employee updatedEmployee=employee.get();
       updatedEmployee.setBalance(employee.get().getBalance()-amount);
        employeeRepository.save(updatedEmployee);
    }


    public List<Employee> getAllEmployee() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public List<Employee> addAllEmployee(List<Employee> employees){
        return (List<Employee>) employeeRepository.saveAll(employees);
    }
}
