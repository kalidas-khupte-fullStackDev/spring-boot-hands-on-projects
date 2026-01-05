package com.bank.app.controller;

import com.bank.app.model.Customer;
import com.bank.app.model.response.ApiResponse;
import com.bank.app.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/customers/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("add")
    public ResponseEntity<ApiResponse<Customer>> createCustomer(@RequestBody Customer customer) {
        try {
            Customer newlyAddedCustomer = customerService.createCustomer(customer);
            String message = "New Customer with a Name: [" + String.join(" " ,newlyAddedCustomer.getFirstName(), newlyAddedCustomer.getLastName()) + "] added Successfully";
            ApiResponse<Customer> apiResponse = new ApiResponse<>(message, newlyAddedCustomer);
            return new ResponseEntity<>(apiResponse,HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
        } catch (Exception e) {
            String errMessage = "Error creating New Customer with a Name: [" + String.join(" " ,customer.getFirstName(), customer.getLastName()) + "], Reason: " + e.getMessage();
            ApiResponse<Customer> apiErrResponse = new ApiResponse<>(errMessage, customer);
            return new ResponseEntity<>(apiErrResponse, HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping("view/all")
    public ResponseEntity<Object> getAllCustomers() {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            if(!customers.isEmpty()){
            return new ResponseEntity<>(customers,HttpStatusCode.valueOf(HttpStatus.OK.value()));
            }else {
            return new ResponseEntity<>("No customers records found in DB",HttpStatusCode.valueOf(HttpStatus.OK.value()));
            }
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @GetMapping("view/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
            return customerService.getCustomerById(customerId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("update/{customerId}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable Long customerId, @RequestBody Customer customer)  {
        try {
            return ResponseEntity.ok(customerService.updateCustomerById(customerId, customer));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/all")
    public ResponseEntity<Object> deleteAllCustomer()  {
        try {
            String deleteAllMessage = customerService.deleteAllCustomer();
            return new ResponseEntity<>(deleteAllMessage,HttpStatusCode.valueOf(HttpStatus.OK.value()));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @DeleteMapping("delete/{customerId}")
    public ResponseEntity<Object> deleteCustomerById(@PathVariable Long customerId)  {
        try {
            String deleteMessage = customerService.deleteCustomerById(customerId);
            return new ResponseEntity<>(deleteMessage,HttpStatusCode.valueOf(HttpStatus.OK.value()));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }


}
