package com.gosuccour.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gosuccour.entity.Car;
import com.gosuccour.entity.Facture;
import com.gosuccour.entity.ItemFacture;
import com.gosuccour.entity.Itv;
import com.gosuccour.entity.Maintenance;
import com.gosuccour.entity.Mechanic;
import com.gosuccour.entity.Plan;
import com.gosuccour.entity.Product;
import com.gosuccour.entity.RequestFactura;
import com.gosuccour.entity.Revision;
import com.gosuccour.entity.Token;
import com.gosuccour.service.IClientService;

@RestController
@RequestMapping("/api")
public class AndroidControllerRestApi {

	@Autowired
	IClientService clientService;

	static HashMap<String, String> listToken = new HashMap<String, String>();

	@RequestMapping("/login")
	public Token authentication(Model model, @RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password) {
		System.out.println(username);
		System.out.println(password);
		int checkLogin = clientService.checkLogin(username, password);
		if (checkLogin == 1) {
			System.out.println("Welcome User !");
			Token token = new Token();
			listToken.put(username, token.getUuid());
			for (Map.Entry<String, String> entry : listToken.entrySet()) {
				System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
			}
			return token;
		} else if (checkLogin == 3) {
			System.out.println("User not exists");
		} else {
			System.out.println("Incorrect User / Password");
		}
		return null;
	}

	@RequestMapping("/idCar")
	public RequestFactura getFacture(@RequestParam(value = "idCar") long idCar, @RequestParam("token") String token) {
		Car car = new Car();
		car = clientService.findOneCar(idCar);

		Facture facture = new Facture();
		facture.setCar(car);
		clientService.saveFacture(facture);

		RequestFactura factura = new RequestFactura();
		factura.setId(facture.getId());

		return factura;
	}

	@RequestMapping("/mantenimiento")
	public Maintenance saveMaintenance(@RequestBody Maintenance maintenance) {
		ItemFacture itemFacture = new ItemFacture();
		List<Product> allProductsBD = clientService.findAllProduct();
		List<Product> productsA = maintenance.getListProducts();
		List<Product> saveProducts = new ArrayList<Product>();
		Double totalProduct = 0.0;
		for (Product product : allProductsBD) {
			for (Product productA : productsA) {
				if (product.getId().equals(productA.getId())) {
					saveProducts.add(product);
					totalProduct += product.getPrice();
				}
			}
		}

		maintenance.setPrice(totalProduct);
		maintenance.setListProducts(saveProducts);
		clientService.saveMaintenance(maintenance);
		itemFacture.setMaintenance(maintenance);
		itemFacture.setFacture_id(maintenance.getFactura_id());
		itemFacture.setPrice(maintenance.getPrice());
		clientService.saveItemFacture(itemFacture);

		return maintenance;

	}

	@RequestMapping("/revision")
	public Revision saveRevision(@RequestBody Revision revision) {
		ItemFacture itemFacture = new ItemFacture();

		Double planPorcentaje = null;
		List<Plan> list = revision.getListPlan();
		for (Plan plan : list) {
			planPorcentaje = plan.getPorcentaje();
		}
		List<Product> allProductsBD = clientService.findAllProduct();
		List<Product> productsA = revision.getListProducts();
		List<Product> saveProducts = new ArrayList<Product>();
		Double totalProduct = 0.0;
		for (Product product : allProductsBD) {
			for (Product productA : productsA) {
				if (product.getId().equals(productA.getId())) {
					System.out.println(revision.getId());
					saveProducts.add(product);
					totalProduct += product.getPrice();
				}
			}
		}
		Double totalPorcentaje = revision.totalPriceRevision(planPorcentaje, totalProduct);
		revision.setPrice(totalPorcentaje);
		revision.setListProducts(saveProducts);
		clientService.saveRevision(revision);// genera revision bd
		itemFacture.setRevision(revision);
		itemFacture.setFacture_id(revision.getFactura_id());
		itemFacture.setPrice(revision.getPrice());
		clientService.saveItemFacture(itemFacture);
		return revision;
	}

	@RequestMapping("/itv")
	public Itv saveItv(@RequestBody Itv itv) {
		ItemFacture itemFacture = new ItemFacture();
		itv.setPrice(120.0);
		clientService.saveItv(itv);
		itemFacture.setPrice(itv.getPrice());
		itemFacture.setFacture_id(itv.getFactura_id());
		clientService.saveItemFacture(itemFacture);
		return itv;
	}

	/* Emergency */
	@RequestMapping("/emergency")
	public Mechanic distanciaCoordenadas(@RequestParam(value = "latitude") double latitude,
			@RequestParam(value = "longitude") double longitude) {
		List<Mechanic> listMechanic = clientService.findAllMechanic();
		Map.Entry<Mechanic, Double> min = null;
		HashMap<Mechanic, Double> increment = new HashMap<>();
		for (Mechanic mechanic : listMechanic) {
			double radioTierra = 6371;
			double dLat = Math.toRadians(mechanic.getLatitude() - latitude);
			double dLng = Math.toRadians(mechanic.getLongitude() - longitude);
			double sindLat = Math.sin(dLat / 2);
			double sindLng = Math.sin(dLng / 2);
			double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(latitude))
					* Math.cos(Math.toRadians(mechanic.getLatitude()));
			double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));

			double distancia = radioTierra * va2;
			System.out.println(distancia);
			increment.put(mechanic, distancia);

		}
		System.out.println();
		for (Map.Entry<Mechanic, Double> map : increment.entrySet()) {
			
			if (min == null || map.getValue().compareTo(min.getValue()) < 0) {
				min = map;
			}
		}

		System.out.println(min.getValue());

		for (Map.Entry<Mechanic, Double> map : increment.entrySet()) {
			if (map.getValue().equals(min.getValue())) {
				return map.getKey();
			}
		}

		return listMechanic.get(0);

	}

}
