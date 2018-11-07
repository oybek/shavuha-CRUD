package com.oybek.shavuha.controllers;

import com.oybek.shavuha.entities.AppId;
import com.oybek.shavuha.entities.Customer;
import com.oybek.shavuha.entities.Deal;
import com.oybek.shavuha.entities.Provider;
import com.oybek.shavuha.services.DealService;
import com.oybek.shavuha.services.ProviderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    private ProviderService providerService;
    private DealService dealService;

    public ProviderController(ProviderService providerService, DealService dealService) {
        this.providerService = providerService;
        this.dealService = dealService;
    }

    @RequestMapping("/save")
    public Provider save(@RequestBody Provider provider) {
        return providerService.save(provider);
    }

    @RequestMapping("/find")
    public Provider find(@RequestParam("app") String app, @RequestParam("id") String id) {
        return providerService.findById(new AppId(app, id)).orElse(null);
    }

    @RequestMapping("/getOpenDeals")
    public List<Deal> getOpenDeals(@RequestBody Provider provider) {
        return dealService.getOpenDeals(provider);
    }
}
