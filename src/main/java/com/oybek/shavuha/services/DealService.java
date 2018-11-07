package com.oybek.shavuha.services;

import com.oybek.shavuha.entities.Customer;
import com.oybek.shavuha.entities.Deal;
import com.oybek.shavuha.entities.Provider;

import java.util.List;
import java.util.Optional;

public interface DealService {
    Optional<Deal> save(Deal deal);
    Optional<Deal> findById(long id);
    Optional<Deal> closeById(long id);

    List<Deal> getOpenDeals(Customer customer);
    List<Deal> getOpenDeals(Provider provider);
}
