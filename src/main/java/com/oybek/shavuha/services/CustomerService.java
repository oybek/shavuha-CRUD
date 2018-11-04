package com.oybek.shavuha.services;

import com.oybek.shavuha.entities.AppId;
import com.oybek.shavuha.entities.Customer;

import java.util.Optional;

public interface CustomerService {
    Customer save(Customer customer);
    Optional<Customer> incBought(AppId id); // increases bought count by id
    Optional<Customer> findById(AppId appId);
    Iterable<Customer> findAll();
}
