package com.oybek.shavuha.services.impl;

import com.oybek.shavuha.entities.AppId;
import com.oybek.shavuha.entities.Customer;
import com.oybek.shavuha.repositories.CustomerRepository;
import com.oybek.shavuha.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> findById(AppId appId) {
        return customerRepository.findById(appId);
    }

    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }
}
