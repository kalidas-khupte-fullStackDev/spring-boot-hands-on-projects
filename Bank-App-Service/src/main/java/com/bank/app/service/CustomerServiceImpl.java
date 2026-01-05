package com.bank.app.service;

import com.bank.app.model.Customer;
import com.bank.app.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements  CustomerService{

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) throws RuntimeException{
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
         return customerRepository.findAll(Sort.by("id"));
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
         return customerRepository.findById(customerId);
    }

    @Override
    public Customer updateCustomerById(Long customerId, Customer newCustomerPayLoad) {
        return customerRepository.findById(customerId).map(existingCustomer -> {
            existingCustomer.setFirstName(newCustomerPayLoad.getFirstName());
            existingCustomer.setLastName(newCustomerPayLoad.getLastName());
            existingCustomer.setEmail(newCustomerPayLoad.getEmail());
            existingCustomer.setPhoneNumber(newCustomerPayLoad.getPhoneNumber());
            return customerRepository.save(existingCustomer);
        }).orElseThrow(()-> new RuntimeException("Customer not found in DB with an Id:" + customerId));
    }

    @Override
    public String deleteCustomerById(Long customerId) {
        if(customerRepository.existsById(customerId)){
            throw new RuntimeException("Customer not found in DB with an Id:" + customerId );
        }
        customerRepository.deleteById(customerId);
        Optional<Customer> op_CustToDelete = customerRepository.findById(customerId);
        if(op_CustToDelete.isPresent()){
            Customer customerToDelete = op_CustToDelete.get();
            return "Customer: " + String.join(" ", customerToDelete.getFirstName(), customerToDelete.getLastName()) + " Deleted Successfully";
        }
        return "";
    }

    @Override
    public String deleteAllCustomer() {
        customerRepository.deleteAll();
        return "All Customers Record Deleted Successfully";
    }
}
