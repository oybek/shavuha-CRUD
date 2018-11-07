package com.oybek.shavuha.services.impl;

import com.oybek.shavuha.entities.Customer;
import com.oybek.shavuha.entities.Deal;
import com.oybek.shavuha.entities.Provider;
import com.oybek.shavuha.repositories.CustomerRepository;
import com.oybek.shavuha.repositories.DealRepository;
import com.oybek.shavuha.repositories.ProviderRepository;
import com.oybek.shavuha.services.DealService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealServiceImpl implements DealService {

    private DealRepository dealRepository;
    private CustomerRepository customerRepository;
    private ProviderRepository providerRepository;

    DealServiceImpl(DealRepository dealRepository
            , CustomerRepository customerRepository
            , ProviderRepository providerRepository) {
        this.dealRepository = dealRepository;
        this.customerRepository = customerRepository;
        this.providerRepository = providerRepository;
    }

    public Optional<Deal> save(Deal deal) {
        Optional<Provider> providerOpt = providerRepository.findById(deal.getProvider().getAppId());
        if (!providerOpt.isPresent()) {
            return Optional.empty();
        }

        Optional<Customer> customerOpt = customerRepository.findById(deal.getCustomer().getAppId());
        if (!customerOpt.isPresent()) {
            return Optional.empty();
        }

        providerOpt.map( provider -> {
            deal.setProvider(provider);
            provider.addDeal(deal);
            return providerRepository.save(provider);
        });

        customerOpt.map( customer -> {
            deal.setCustomer(customer);
            customer.addDeal(deal);
            return customerRepository.save(customer);
        });

        return Optional.ofNullable(dealRepository.save(deal));
    }

    public Optional<Deal> findById(long id) {
        return dealRepository.findById(id);
    }

    public Optional<Deal> closeById(long id) {
        return dealRepository.findById(id).map(x -> dealRepository.save(x.close()));
    }

    public List<Deal> getOpenDeals(Customer customer) {
        return dealRepository.findByCustomerAndOpenTrue(customer);
    }

    public List<Deal> getOpenDeals(Provider provider) {
        return dealRepository.findByProviderAndOpenTrue(provider);
    }
}
