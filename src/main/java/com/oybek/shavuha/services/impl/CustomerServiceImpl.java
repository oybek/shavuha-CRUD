package com.oybek.shavuha.services.impl;

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

    public Customer incBought(long id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        return customerOpt.map(x -> customerRepository.save(x.increaseBought())).orElse(null);
    }

    public Optional<Customer> findById(long id) {
        return customerRepository.findById(id);
    }

    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }
}
