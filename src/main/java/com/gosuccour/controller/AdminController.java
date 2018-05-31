package com.gosuccour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

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

		model.addAttribute("titul", "Client List");
		// model.addAttribute("clients", clientService.findAll()); sin paginacion
		model.addAttribute("clients", clients);
		model.addAttribute("page", pageRender);
		return "clients";

	}


	@RequestMapping(value = "/mechanics", method = RequestMethod.GET)
	public String listMechanic(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		// page numero de pagina actual, size(5) elementos por pagina
		Pageable pageableRequest = PageRequest.of(page, 4);
		Page<Mechanic> mechanics=clientService.findAllMechanic(pageableRequest);
		PageRender<Mechanic> pageRender = new PageRender<Mechanic>("/mechanics", mechanics);

		model.addAttribute("titul", "Mechanics");
		// model.addAttribute("clients", clientService.findAll()); sin paginacion
		model.addAttribute("mechanics", mechanics);
		model.addAttribute("page", pageRender);
		return "mechanics";

	}

}
