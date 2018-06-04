package com.gosuccour.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gosuccour.entity.Car;
import com.gosuccour.entity.Client;
import com.gosuccour.entity.Mechanic;
import com.gosuccour.service.IClientService;
import com.gosuccour.util.paginator.PageRender;

@Controller
public class AdminController {

	@Autowired
	private IClientService clientService;

	@RequestMapping(value = "clients", method = RequestMethod.GET)
	public String listClient(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		// page numero de pagina actual, size(5) elementos por pagina
		Pageable pageableRequest = PageRequest.of(page, 4);
		Page<Client> clients = clientService.findAll(pageableRequest);
		PageRender<Client> pageRender = new PageRender<Client>("/clients", clients);

		model.addAttribute("titul", "Clients");
		// model.addAttribute("clients", clientService.findAll()); sin paginacion
		model.addAttribute("clients", clients);
		model.addAttribute("page", pageRender);
		return "clients";

	}

	@RequestMapping(value = "/mechanics", method = RequestMethod.GET)
	public String listMechanic(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		// page numero de pagina actual, size(5) elementos por pagina
		Pageable pageableRequest = PageRequest.of(page, 4);
		Page<Mechanic> mechanics = clientService.findAllMechanic(pageableRequest);
		PageRender<Mechanic> pageRender = new PageRender<Mechanic>("/mechanics", mechanics);

		model.addAttribute("titul", "Mechanics");
		// model.addAttribute("clients", clientService.findAll()); sin paginacion
		model.addAttribute("mechanics", mechanics);
		model.addAttribute("page", pageRender);
		return "mechanics";

	}

	@GetMapping(value = "/seeClientAdmin/{id}")
	public String seeClientAdmin(@PathVariable(value = "id") Long id, Map<String, Object> model,
			RedirectAttributes flash) {
		Client client = clientService.findOne(id);
		if (client == null) {
			flash.addFlashAttribute("danger", "The Client not exist BD¡");
			return "redirect:/clients";
		}
		model.put("client", client);
		model.put("titul", "Welcome " + client.getSurname());
		return "seeClientAdmin";
	}

	@GetMapping(value = "/seeMechanic/{id}")
	public String seeMechanicAdmin(@PathVariable(value = "id") Long id, Map<String, Object> model,
			RedirectAttributes flash) {
		Mechanic mechanic = clientService.findOneMechanic(id);
		if (mechanic == null) {
			flash.addFlashAttribute("danger", "The Client not exist BD¡");
			return "redirect:/mechanics";
		}
		model.put("mechanic", mechanic);
		model.put("titul", "Admin");
		return "seeMechanic";
	}

	@GetMapping("/deleteClientAdmin/{id}")
	public String deleteClient(@PathVariable(value = "id") Long id) {
		Client client = clientService.findOne(id);
		if (client != null) {
			clientService.delete(id);
		}
		return "redirect:/clients";
	}

	@GetMapping("/deleteCarAdmin/{id}")
	public String deleteCar(@PathVariable(value = "id") Long id) {
		Car car = clientService.findOneCar(id);
		if (car != null) {
			clientService.deleteCar(id);
		}
		return "redirect:/seeMechanic/" + car.getClient().getId();
	}

	@GetMapping("/deleteMechanic/{id}")
	public String deleteMechanic(@PathVariable(value = "id") Long id) {
		Mechanic mechanic = clientService.findOneMechanic(id);
		if (mechanic != null) {
			clientService.deleteMechanic(id);
		}
		return "redirect:/mechanics/";
	}

}
