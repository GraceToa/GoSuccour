package com.gosuccour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gosuccour.entity.Car;
import com.gosuccour.entity.Client;
import com.gosuccour.service.IClientService;

@RestController
@RequestMapping("/api")
public class ClientControllerApiRest {
	
	@Autowired
	IClientService clientService;
	
	@GetMapping("/client/{id}")
	public  Client getClient(@PathVariable Long id) {	
		Client client= clientService.findOne(id);
		return client;	
	}

	@GetMapping("/car/{id}")
	public  Car getCar(@PathVariable Long id) {	
		Car car= clientService.findOneCar(id);
		return car;	
	}

}
