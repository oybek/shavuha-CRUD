package com.oybek.shavuha.controllers;

import com.oybek.shavuha.entities.Customer;
import com.oybek.shavuha.services.CustomerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    private CustomerService customerService;

	WebController(CustomerService customerService) {
	    this.customerService = customerService;
	}

	@RequestMapping("/testInit")
	public String testInit(){
		customerService.save(new Customer("Jack", "Smith"));
		customerService.save(new Customer("Adam", "Johnson"));
		customerService.save(new Customer("Tom", "Cruse"));
		customerService.save(new Customer("Brett", "Pit"));
		customerService.save(new Customer("Will", "Smith"));

		return "ok";
	}

	@RequestMapping("/createCustomer")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerService.save(customer);
	}

	@RequestMapping("/findById")
	public Customer findById(@RequestParam("id") long id) {
		return customerService.findById(id).orElse(null);
	}

	@RequestMapping("/incBought")
	public Customer incBought(@RequestParam("id") long id) {
		return customerService.incBought(id);
	}

	@RequestMapping("/findAll")
	public Iterable<Customer> findAll() {
	    return customerService.findAll();
	}
}

