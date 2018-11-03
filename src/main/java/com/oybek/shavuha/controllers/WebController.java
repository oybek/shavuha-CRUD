package com.oybek.shavuha.controllers;

import com.oybek.shavuha.entities.Customer;
import com.oybek.shavuha.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	private CustomerRepository repository;

	WebController(CustomerRepository repository) {
	    this.repository = repository;
	}

	@RequestMapping("/init")
	public String process(){
		repository.save(new Customer("Jack", "Smith"));
		repository.save(new Customer("Adam", "Johnson"));
		repository.save(new Customer("Tom", "Cruse"));
		repository.save(new Customer("Brett", "Pit"));
		repository.save(new Customer("Will", "Smith"));

		return "ok";
	}

	@RequestMapping("/findAll")
	public Iterable<Customer> findAll(){
	    return repository.findAll();
	}

	@RequestMapping("/findById")
	public Customer findById(@RequestParam("id") long id) {
		return repository.findById(id).orElse(null);
	}

	@RequestMapping("/findByLastname")
	public String fetchDataByLastName(@RequestParam("lastname") String lastName){
		String result = "";

		for(Customer cust: repository.findByLastName(lastName)){
			result += cust.toString() + "<br>";
		}

		return result;
	}
}

