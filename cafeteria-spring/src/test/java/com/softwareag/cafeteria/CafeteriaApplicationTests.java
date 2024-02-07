package com.softwareag.cafeteria;

import com.softwareag.cafeteria.model.Employee;
import com.softwareag.cafeteria.model.Product;
import com.softwareag.cafeteria.repository.EmployeeRepository;
import com.softwareag.cafeteria.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CafeteriaApplicationTests {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ProductRepository productRepository;

	@Test
	public void testCreateEmployee(){

		Employee e =new Employee("5005","kumar","1234",200,"USER");
		employeeRepository.save(e);
		assertNotNull(employeeRepository.findById("5005").get());

	}

	@Test
	public void testCreateProduct(){

		Product p =new Product(null,"tea",15,"sampleurl");
		productRepository.save(p);
		List<Product> list =productRepository.findAll();
		assertThat(list).size().isGreaterThan(0);

	}

}


