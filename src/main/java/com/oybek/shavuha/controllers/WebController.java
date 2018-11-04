package com.oybek.shavuha.controllers;

import com.oybek.shavuha.entities.AppId;
import com.oybek.shavuha.entities.Customer;
import com.oybek.shavuha.entities.Provider;
import com.oybek.shavuha.services.CustomerService;
import com.oybek.shavuha.services.ProviderService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class WebController {

    private CustomerService customerService;
    private ProviderService providerService;

	WebController(CustomerService customerService, ProviderService providerService) {
	    this.customerService = customerService;
	    this.providerService = providerService;
	}

	@RequestMapping("/testInit")
	public String testInit(){
		customerService.save(new Customer(new AppId("vk", "123"), "Jack", "Smith"));

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

	@RequestMapping("/findCustomer")
	public Customer findCustomer(@RequestParam("app") String app
			, @RequestParam("id") String id) {
		return customerService.findById(new AppId(app, id)).orElse(null);
	}

	@RequestMapping("/incBought")
	public Optional<?> incBought(@RequestParam("app") String app
			, @RequestParam("id") String id) {
		return customerService.incBought(new AppId(app, id));
	}

	@RequestMapping("/findAll")
	public Iterable<Customer> findAll() {
	    return customerService.findAll();
	}
}

