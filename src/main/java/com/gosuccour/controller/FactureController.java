package com.gosuccour.controller;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gosuccour.entity.Car;
import com.gosuccour.entity.Facture;
import com.gosuccour.entity.ItemFacture;
import com.gosuccour.entity.Itv;
import com.gosuccour.entity.Maintenance;
import com.gosuccour.entity.Plan;
import com.gosuccour.entity.Product;
import com.gosuccour.entity.Revision;
import com.gosuccour.service.IClientService;


@Controller
@SessionAttributes("car")
@RequestMapping("/facture")
public class FactureController {
	
	@Autowired
	IClientService clientService;
	
	@GetMapping(value="/facture/{carId}")
	public String goFormService(@PathVariable(name="carId")Long id, Model model) {
		Car car = clientService.findOneCar(id);
		if (car == null) {
			return "redirect:seeClient";
		}
		Facture facture = new Facture();
		facture.setCar(car);
		facture.setPrice(0.0);
		clientService.saveFacture(facture);
		model.addAttribute("facture", facture);
		model.addAttribute("titul", "Facture for Services");
		return "facture/facture";
		
	}
	
	/*Mantenimiento*/
	@GetMapping(value="/maintenance/{factureId}")
	public String goFormMaintenance(@PathVariable(name="factureId")Long factureId,Model model) {
		Maintenance maintenance=new Maintenance();
		Facture facture = clientService.finOneFacture(factureId);
		List<Product>allProducts=clientService.findAllProduct();
		maintenance.setListProducts(allProducts);
		maintenance.setPrice(0.0);
		model.addAttribute("maintenance", maintenance);
		model.addAttribute("allProducts", allProducts);
		model.addAttribute("facture", facture);
		model.addAttribute("msg", facture.getId());
		model.addAttribute("titul", "Service Maintenance for: ");
		return "facture/formMaintenance";		
	}
		
	@PostMapping(value="/itemFacture")
	public String saveFormMaintenance(Model model,@Valid Maintenance maintenance, @RequestParam Map<String, String> reqPar ) {
		
		String facture_id=reqPar.get("idFacture");
		Long factureId=Long.parseLong(facture_id);
		Facture facture = clientService.finOneFacture(factureId);
		Car car = facture.getCar();
		ItemFacture itemFacture = new ItemFacture();	
		Double total = 0.0;
		List<Product>list =maintenance.getListProducts();
		for (Product product : list) {
		 total += product.getPrice();			
		}
		maintenance.setPrice(total);
		clientService.saveMaintenance(maintenance);//guarda maintenance
		itemFacture.setMaintenance(maintenance);
		itemFacture.setFacture_id(factureId);
		itemFacture.setPrice(maintenance.getPrice());
		clientService.saveItemFacture(itemFacture);
		model.addAttribute("maintenance", maintenance);
		model.addAttribute("car", car);
		model.addAttribute("itemFacture", itemFacture);
		model.addAttribute("msg", facture_id);
		model.addAttribute("titul", "    MAINTENANCE ");
		return "facture/factureMaintenance";		
	}
	
	/*Revision*/
	@GetMapping(value="revision/{factureId}")
	public String goFormRevision(Model model, @PathVariable(name="factureId")Long factureId) {
		Revision revision = new Revision();
		Facture facture = clientService.finOneFacture(factureId);
		List<Product>allProducts=clientService.findAllProduct();
		revision.setListProducts(allProducts);
		List<Plan>allPlan= clientService.findAllPlan();
		model.addAttribute("revision", revision);
		model.addAttribute("allPlan", allPlan);
		model.addAttribute("allProducts", allProducts);
		model.addAttribute("facture", facture);
		model.addAttribute("msg", facture.getId());
		model.addAttribute("titul", "Service Revision for: ");
		return "facture/formRevision";
		
	}
	
	@PostMapping(value="/itemRevision")
	public String saveFormRevision(Model model,@Valid Revision revision, @RequestParam Map<String, String> reqPar, BindingResult result ) {

		if (result.hasErrors()) {
			model.addAttribute("titul", "Service Revision for:");
			return "facture/formRevision";
		}

		String facture_id=reqPar.get("idFacture");
		Long factureId=Long.parseLong(facture_id);
		Facture facture = clientService.finOneFacture(factureId);
		Car car = facture.getCar();
		ItemFacture itemFacture = new ItemFacture();	
		
		Double planPorcentaje = null;
		List<Plan>list =revision.getListPlan();
		for (Plan plan : list) {
			planPorcentaje= plan.getPorcentaje();	
		}
		System.out.println(planPorcentaje);
		Double totalProduct = 0.0;
		List<Product>listP =revision.getListProducts();
		for (Product product : listP) {
		 totalProduct += product.getPrice();			
		}
		Double totalPorcentaje = revision.totalPriceRevision(planPorcentaje, totalProduct);
		revision.setPrice(totalPorcentaje);
		clientService.saveRevision(revision);//genera revision bd
		itemFacture.setRevision(revision);
		itemFacture.setFacture_id(factureId);
		itemFacture.setPrice(revision.getPrice());
		clientService.saveItemFacture(itemFacture);
		model.addAttribute("revision", revision);
		model.addAttribute("car", car);
		model.addAttribute("msg", factureId);
		model.addAttribute("titul", "    REVISION ");
		return "facture/factureRevision";		
	}

	/*Itv*/
	@GetMapping(value="/formItv/{factureId}")
	public String goFormItv(Model model, @PathVariable(name="factureId")Long factureId) {
		Facture facture = clientService.finOneFacture(factureId);
		Itv itv = new Itv();
		Car car = facture.getCar();	
		String carMatriculation = car.getMatriculation();
		int calculItv=Car.calculItvYear(carMatriculation);
		String calcuItv= String.valueOf(calculItv);
		itv.setYear(calcuItv);
		model.addAttribute("facture", facture);
		model.addAttribute("car", car);
		model.addAttribute("itv", itv);
		model.addAttribute("msg", facture.getId());
		model.addAttribute("titul", " Service  ITV ");
		return "facture/formItv";	
	}
	
	@PostMapping(value="/saveformItv")
	public String saveItv(Model model, @Valid Itv itv, @RequestParam Map<String, String> reqPar ) {
		String facture_id=reqPar.get("idFacture");
		Long factureId=Long.parseLong(facture_id);
		Facture facture = clientService.finOneFacture(factureId);
		Car car = facture.getCar();
		ItemFacture itemFacture = new ItemFacture();	
		itv.setPrice(120.0);
		clientService.saveItv(itv);
		itemFacture.setPrice(itv.getPrice());
		itemFacture.setFacture_id(factureId);
		clientService.saveItemFacture(itemFacture);
		model.addAttribute("itv", itv);
		model.addAttribute("car", car);
		model.addAttribute("msg", facture_id);
		model.addAttribute("titul", "    ITV ");
		return "facture/factureItv";
		
	}
	
	
	/*Emergency*/
	
	@GetMapping(value="/emergency/{factureId}")
	public String goEmergency(Model model, @PathVariable(name="factureId")Long factureId) {
		Facture facture = clientService.finOneFacture(factureId);
		Car car = facture.getCar();	
		model.addAttribute("car", car);
		model.addAttribute("titul", "    EMERGENCY ");
		return "facture/emergency";
		
	}
	
	
	
	/*Back Services*/
	@GetMapping(value="/formBack/{factureId}")
	public String goBackFormService(@PathVariable(name="factureId")Long factureId, Model model) {
		Facture facture = clientService.finOneFacture(factureId);
		if (facture==null) {
			return "redirect:seeClient";
		}
		Double price = facture.getTotal();
		facture.setPrice(price);
		model.addAttribute("facture", facture);
		model.addAttribute("titul", "Facture for Services");
		return "facture/facture";
		
	}
	
	@GetMapping("/seeFacture/{idFacture}")
	public String seeFacture(@PathVariable(value="idFacture")Long idFacture,Model model) {
		Facture facture= clientService.findFactureById(idFacture);
		model.addAttribute("facture", facture);
		model.addAttribute("titul","Facture: "+ facture.getId());
		return "facture/seeFacture";
		
	}
	
	
	
	
	
	
	

}
