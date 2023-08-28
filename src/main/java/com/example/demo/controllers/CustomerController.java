package com.example.demo.controllers;

import com.example.demo.domain.Customer;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/customer";

    @Autowired
    private CustomerService service;

    @PostMapping
    public ResponseEntity<Customer> createNewCustomer(@RequestBody final Customer customer) {
        return new ResponseEntity<>(service.createNewCustomer(customer), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Customer> patchCustomer(@PathVariable final String id, @RequestBody final Customer customer) {
        return new ResponseEntity<>(service.patchCustomer(Long.valueOf(id), customer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable final String id) {
        service.deleteCustomerById(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
