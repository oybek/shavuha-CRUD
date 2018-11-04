package com.oybek.shavuha.controllers;

import com.oybek.shavuha.entities.AppId;
import com.oybek.shavuha.entities.Customer;
import com.oybek.shavuha.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class WebController {

    private CustomerService customerService;

	WebController(CustomerService customerService) {
	    this.customerService = customerService;
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

	@RequestMapping("/findById")
	public Customer findById(@RequestParam("app") String app,
							 @RequestParam("id") String id) {
		return customerService.findById(new AppId(app, id)).orElse(null);
	}

	@RequestMapping("/incBought")
	public Optional<?> incBought(@RequestParam("app") String app,
                                 @RequestParam("id") String id) {
		return customerService.incBought(new AppId(app, id));
	}

	@RequestMapping("/findAll")
	public Iterable<Customer> findAll() {
	    return customerService.findAll();
	}
}

