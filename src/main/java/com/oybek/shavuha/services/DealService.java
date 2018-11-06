package com.oybek.shavuha.services;

import com.oybek.shavuha.entities.AppId;
import com.oybek.shavuha.entities.Customer;
import com.oybek.shavuha.entities.Deal;

import java.util.Optional;

public interface DealService {
    Optional<Deal> save(Deal deal);
    Optional<Deal> findById(long id);
    Optional<Deal> closeById(long id);

    // returns true if customer has uncomplete deals
    boolean hasOpenDeal(Customer customer);

    long countByCustomer(AppId appId);
}
