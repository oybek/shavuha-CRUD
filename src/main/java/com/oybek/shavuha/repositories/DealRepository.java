package com.oybek.shavuha.repositories;

import com.oybek.shavuha.entities.Deal;
import com.oybek.shavuha.entities.Provider;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DealRepository extends CrudRepository<Deal, Long> {
    List<Deal> getByProvider(Provider provider);
}
