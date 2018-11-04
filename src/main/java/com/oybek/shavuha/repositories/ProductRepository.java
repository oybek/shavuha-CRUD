package com.oybek.shavuha.repositories;

import com.oybek.shavuha.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
