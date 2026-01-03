package com.bank.app.service;

import com.bank.app.model.Customer;
import com.bank.app.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements  CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public String createCustomer(Customer customer) {
        customerRepository.save(customer);
        return "New Customer added Successfully";
    }

    @Override
    public List<Customer> getAllCustomers() {
         return customerRepository.findAll(Sort.by("id"));
    }

    @Override
    public Customer getCustomerById(Long customerId) {
         return customerRepository.findById(customerId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found in DB with an Id:" + customerId.toString() ));
    }

    @Override
    public String updateCustomer(Long customerId, Customer newCustomerPayLoad) {
         Optional<Customer> op_CustToUpdate = customerRepository.findById(customerId);
         Customer customerToUpdate = op_CustToUpdate.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found in DB with an Id:" + customerId.toString() ));
        BeanUtils.copyProperties(newCustomerPayLoad, customerToUpdate, "id");
        customerRepository.save(customerToUpdate);
        return customerId + " Details has been Updated Successfully";
    }

    @Override
    public String deleteCustomer(Long customerId) {
        Optional<Customer> op_CustToDelete = customerRepository.findById(customerId);
        Customer customerToDelete = op_CustToDelete.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found in DB with an Id:" + customerId.toString() ));
        customerRepository.delete(customerToDelete);
        return "Customer: " + String.join(" ", customerToDelete.getFirstName(), customerToDelete.getLastName()) + " Deleted Successfully";
    }

    @Override
    public String deleteAllCustomer() {
        customerRepository.deleteAll();
        return "All Customers Record Deleted Successfully";
    }
}
