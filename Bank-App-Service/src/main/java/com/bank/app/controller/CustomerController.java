package com.bank.app.controller;

import com.bank.app.model.Customer;
import com.bank.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/customers/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("add")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        try {
            String message = customerService.createCustomer(customer);
            return new ResponseEntity<>(message,HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
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
    public ResponseEntity<Object> getCustomerById(@PathVariable Long customerId) {
        try {
            Customer customerById = customerService.getCustomerById(customerId);
            return new ResponseEntity<>(customerById,HttpStatusCode.valueOf(HttpStatus.OK.value()));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @PutMapping("update/{customerId}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer)  {
        try {
            String updateMessage = customerService.updateCustomer(customerId, customer);
            return new ResponseEntity<>(updateMessage,HttpStatusCode.valueOf(HttpStatus.OK.value()));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
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
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long customerId)  {
        try {
            String deleteMessage = customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(deleteMessage,HttpStatusCode.valueOf(HttpStatus.OK.value()));
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }


}
