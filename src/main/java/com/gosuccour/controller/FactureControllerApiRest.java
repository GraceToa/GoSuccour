package com.gosuccour.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gosuccour.entity.Car;
import com.gosuccour.entity.Facture;
import com.gosuccour.entity.Maintenance;
import com.gosuccour.service.IClientService;

@RestController
@RequestMapping("api/facture")
public class FactureControllerApiRest {
	
	@Autowired
	IClientService clientService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Maintenance> getAllMaintenance() {
		return clientService.finAllMaintenance();
		
	}
	
	@GetMapping("/maintenance/{id}")
	public Maintenance getMaintenance(@PathVariable Long id) {
		Maintenance maintenance=clientService.findOneMaintenance(id);
		return maintenance;		
	}
	
	@GetMapping("/facture/{id}")
	public Facture getFacture(@PathVariable Long id) {
		Facture facture = clientService.finOneFacture(id);
		return facture;
		
	}
	
	@GetMapping("/car/{id}")
	public Car getCar(@PathVariable Long id) {
		Car car = clientService.findOneCar(id);
		return car;
		
	}
}
