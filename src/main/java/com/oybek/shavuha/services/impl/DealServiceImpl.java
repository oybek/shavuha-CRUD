package com.oybek.shavuha.services.impl;

import com.oybek.shavuha.entities.AppId;
import com.oybek.shavuha.entities.Customer;
import com.oybek.shavuha.entities.Deal;
import com.oybek.shavuha.repositories.CustomerRepository;
import com.oybek.shavuha.repositories.DealRepository;
import com.oybek.shavuha.services.DealService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DealServiceImpl implements DealService {

    private DealRepository dealRepository;
    private CustomerRepository customerRepository;

    DealServiceImpl(DealRepository dealRepository, CustomerRepository customerRepository) {
        this.dealRepository = dealRepository;
        this.customerRepository = customerRepository;
    }

    public Optional<Deal> save(Deal deal) {
        return Optional.ofNullable(dealRepository.save(deal));
    }

    public Optional<Deal> findById(long id) {
        return dealRepository.findById(id);
    }

    public Optional<Deal> closeById(long id) {
        return dealRepository.findById(id).map(x -> dealRepository.save(x.close()));
    }

    public boolean hasOpenDeal(Customer customer) {
        return !dealRepository.findByCustomerAndClosedFalse(customer).isEmpty();
    }

    public long countByCustomer(AppId appId) {
        return customerRepository.findById(appId).map(x -> dealRepository.countByCustomer(x)).orElse(0L);
    }
}
