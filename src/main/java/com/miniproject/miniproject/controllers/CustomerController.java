package com.miniproject.miniproject.controllers;

import com.miniproject.miniproject.dtos.customer.*;
import com.miniproject.miniproject.responses.RestResponse;
import com.miniproject.miniproject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<CustomerDto>>> getAllCustomer() {
        return new ResponseEntity<>(
                new RestResponse<>(
                        service.getAllCustomer(),
                        "Successfully.",
                        "200"),
                HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<RestResponse<List<CustomerDto>>> getAllCustomerByName(@PathVariable String name) {
        return new ResponseEntity<>(
                new RestResponse<>(
                        service.getAllCustomerByName(name),
                        "Successfully.",
                        "200"),
                HttpStatus.OK);
    }

    @GetMapping("/account-number/{accountNumber}")
    public ResponseEntity<RestResponse<CustomerDto>> getCustomerByAccountNumber(@PathVariable String accountNumber) {
        return new ResponseEntity<>(
                new RestResponse<>(
                        service.getCustomerByAccountNumber(accountNumber),
                        "Successfully.",
                        "200"),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestResponse<CustomerDto>> createCustomer(@RequestBody CreateCustomerDto newCustomer) {
        return new ResponseEntity<>(
                new RestResponse<>(service.createCustomer(newCustomer),
                        "Create new customer successfully.",
                        "201"),
                HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RestResponse<CustomerDto>> updateNasabah(@RequestBody UpdateCustomerDto oldCustomer) {
        return new ResponseEntity<>(
                new RestResponse<>(service.updateCustomer(oldCustomer),
                        "Update customer successfully.",
                        "201"),
                HttpStatus.CREATED);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<RestResponse<DeleteCustomerReponseDto>> deleteNasabah(@PathVariable String userId) {
        return new ResponseEntity<>(
                new RestResponse<>(service.deleteCustomer(userId),
                        "Delete customer successfully.",
                        "201"),
                HttpStatus.OK);
    }
}
