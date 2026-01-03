package com.bank.app.service;

import com.bank.app.model.Customer;

import java.util.List;

public interface CustomerService {
    String createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long customerId);
    String updateCustomer(Long customerId, Customer customer);
    String deleteCustomer(Long customerId);
    String deleteAllCustomer();
}
