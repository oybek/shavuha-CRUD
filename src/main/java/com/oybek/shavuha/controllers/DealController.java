package com.oybek.shavuha.controllers;

import com.oybek.shavuha.entities.*;
import com.oybek.shavuha.services.DealService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/deal")
public class DealController {

	private DealService dealService;

	DealController(DealService dealService) {
	    this.dealService = dealService;
	}

	@RequestMapping("/save")
	public Optional<Deal> save(@RequestBody Deal deal) {
	    return dealService.save(deal);
	}

	@RequestMapping("/find")
	public Optional<Deal> find(@RequestParam long id) {
	    return dealService.findById(id);
	}

	@RequestMapping("/close")
	public Optional<Deal> close(@RequestParam long id) {
		return dealService.closeById(id);
	}
}
