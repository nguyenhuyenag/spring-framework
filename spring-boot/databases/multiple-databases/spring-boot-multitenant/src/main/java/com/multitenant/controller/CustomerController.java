package com.multitenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multitenant.domain.Customer;
import com.multitenant.repository.CustomerRepository;

@RestController
@Transactional
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(path = "/employee")
    public ResponseEntity<?> createEmployee() {
        Customer newEmployee = new Customer();
        newEmployee.setCustomerName("Baeldung");
        customerRepository.save(newEmployee);
        return ResponseEntity.ok(newEmployee);
    }
}
