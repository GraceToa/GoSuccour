package com.gosuccour.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gosuccour.entity.Car;
import com.gosuccour.entity.Client;
import com.gosuccour.entity.Facture;
import com.gosuccour.entity.ItemFacture;
import com.gosuccour.entity.Maintenance;
import com.gosuccour.entity.Revision;
import com.gosuccour.service.IClientService;

@Controller
@RequestMapping("/car")
@SessionAttributes("car")
public class CarController {

	@Autowired
	private IClientService clientService;

	@GetMapping("/form/{clientId}")
	public String goFormCar(@PathVariable(value = "clientId") Long clientId, Map<String, Object> model,
			RedirectAttributes flash) {
		Map<String, String> modelMap = new HashMap<String, String>();
		modelMap.put("Alpina", "Alpina");
		modelMap.put("Audi", "Audi");
		modelMap.put("Bmw", "Bmw");
		modelMap.put("Cadillac", "Cadillac");
		modelMap.put("Chevrolet", "Chevrolet");
		modelMap.put("Citroen", "Citroen");
		modelMap.put("Fiat", "Fiat");
		modelMap.put("Ford", "Ford");
		modelMap.put("Honda", "Honda");
		modelMap.put("Hyundai", "Hyundai");
		modelMap.put("Lancia", "Lancia");
		modelMap.put("Suzuki", "Suzuki");
		modelMap.put("Toyota", "Toyota");

		Map<String, String> pneumaticMap = new HashMap<String, String>();
		pneumaticMap.put("115/70 R15 90 M", "115/70 R15 90 M");
		pneumaticMap.put("205/45 R17 84 V", "205/45 R17 84 V");
		pneumaticMap.put("255/5 R16 91 V", "255/5 R16 91 V");
		pneumaticMap.put("275/50 R20 113 Y", "275/50 R20 113 Y");
		pneumaticMap.put("345/35 R18 109 Y", "345/35 R18 109 Y");

		Map<String, String> motorMap = new HashMap<String, String>();
		motorMap.put("Gasolina", "Gasolina");
		motorMap.put("Diesel", "Diesel");
		motorMap.put("Hibrido", "Hibrido");
		motorMap.put("Electrico", "Electrico");

		Map<String, String> displacementMap = new HashMap<String, String>();
		displacementMap.put("1.4 120cv", "1.4 120cv");
		displacementMap.put("1.6 90cv", "1.6 90cv");
		displacementMap.put("1.6 185cv", "1.6 185cv");
		displacementMap.put("2.0 240cv", "2.0 240cv");

		Client client = clientService.findOne(clientId);
		if (client == null) {
			flash.addFlashAttribute("danger", "The Client not exist in BD¡");
			return "redirect:/formClient";
		}
		Car car = new Car();
		car.setClient(client);
		model.put("car", car);
		model.put("modelMap", modelMap);
		model.put("pneumaticMap", pneumaticMap);
		model.put("motorMap", motorMap);
		model.put("displacementMap", displacementMap);
		model.put("titul", "Create Car");
		return "car/form";

	}

	@PostMapping("/form")
	public String saveFormCar(@Valid Car car, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titul", "Create Car");
			return "car/form";
		}
		clientService.saveCar(car);
		status.setComplete();
		return "redirect:/seeClient/" + car.getClient().getId();
	}

	@GetMapping("/delete/{id}")
	public String deleteCar(@PathVariable(value = "id") Long id) {
		Car car = clientService.findOneCar(id);
		if (car != null) {
			clientService.deleteCar(id);
		}
		return "redirect:/seeClient/" + car.getClient().getId();
	}
	
	@GetMapping("/seeCar/{id}")
	public String getCar(@PathVariable(name = "id") Long id, Model model) {
		Car car = clientService.findOneCar(id);
		model.addAttribute("car", car);
		return "car/seeCar";
		
	}
}
