package com.bank.app.service;

import com.bank.app.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long customerId);
    Customer updateCustomerById(Long customerId, Customer customer);
    String deleteCustomerById(Long customerId);
    String deleteAllCustomer();
}
