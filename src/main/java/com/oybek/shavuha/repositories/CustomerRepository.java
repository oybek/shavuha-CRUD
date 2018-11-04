package com.oybek.shavuha.repositories;

import com.oybek.shavuha.entities.AppId;
import com.oybek.shavuha.entities.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, AppId> {
	List<Customer> findByLastName(String lastName);
}

