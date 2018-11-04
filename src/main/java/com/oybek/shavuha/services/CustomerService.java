package com.oybek.shavuha.services;

import com.oybek.shavuha.entities.Customer;

import java.util.Optional;

public interface CustomerService {
    Customer save(Customer customer);
    Customer incBought(long id); // increases bought count by id
    Optional<Customer> findById(long id);
    Iterable<Customer> findAll();
}
