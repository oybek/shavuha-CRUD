package com.oybek.shavuha.controllers;

import com.oybek.shavuha.entities.AppId;
import com.oybek.shavuha.entities.Customer;
import com.oybek.shavuha.entities.Deal;
import com.oybek.shavuha.services.CustomerService;
import com.oybek.shavuha.services.DealService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;
    private DealService dealService;

    CustomerController(CustomerService customerService
            , DealService dealService) {
        this.customerService = customerService;
        this.dealService = dealService;
    }

    @RequestMapping("/save")
    public Customer save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @RequestMapping("/find")
    public Customer find(@RequestParam("app") String app, @RequestParam("id") String id) {
        return customerService.findById(new AppId(app, id)).orElse(null);
    }

    @RequestMapping("/getOpenDeals")
    public List<Deal> getOpenDeals(@RequestBody Customer customer) {
        return dealService.getOpenDeals(customer);
    }
}
