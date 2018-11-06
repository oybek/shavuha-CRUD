package com.oybek.shavuha.controllers;

import com.oybek.shavuha.entities.*;
import com.oybek.shavuha.services.CustomerService;
import com.oybek.shavuha.services.DealService;
import com.oybek.shavuha.services.ProviderService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
public class WebController {

	private DealService dealService;
    private CustomerService customerService;
    private ProviderService providerService;

	WebController(CustomerService customerService, ProviderService providerService, DealService dealService) {
	    this.customerService = customerService;
	    this.providerService = providerService;
	    this.dealService = dealService;
	}

	@RequestMapping("/testInit")
	public String testInit(){
		customerService.save(new Customer(new AppId("vk", "123"), "Jack", "Smith"));
		providerService.save(new Provider(
				new AppId("vk", "123")
				, "Doner"
				, "Jack"
				, "Smith"
				, 0.0f
				, 0.0f
				, new HashSet<Product>(Arrays.asList(
						new Product("Классическая", 100)
						, new Product("Сырная", 120)))
		));

		return "ok";
	}

	@RequestMapping("/createCustomer")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerService.save(customer);
	}

	@RequestMapping("/createProvider")
	public Provider createProvider(@RequestBody Provider provider) {
		return providerService.save(provider);
	}

	@RequestMapping("/createDeal")
	public Optional<Deal> createDeal(@RequestBody Deal deal) {
	    return dealService.save(deal);
	}

	@RequestMapping("/findDeal")
	public Optional<Deal> findDeal(long id) {
	    return dealService.findById(id);
	}

	@RequestMapping("/findCustomer")
	public Customer findCustomer(@RequestParam("app") String app
			, @RequestParam("id") String id) {
		return customerService.findById(new AppId(app, id)).orElse(null);
	}

	@RequestMapping("/findAllCustomers")
	public Iterable<Customer> findAllCustomers() {
	    return customerService.findAll();
	}

	@RequestMapping("/findAllProviders")
	public Iterable<Provider> findAllProviders() {
		return providerService.findAll();
	}
}

