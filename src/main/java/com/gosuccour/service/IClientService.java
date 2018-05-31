package com.gosuccour.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gosuccour.entity.Car;
import com.gosuccour.entity.Client;
import com.gosuccour.entity.Coordenadas;
import com.gosuccour.entity.Facture;
import com.gosuccour.entity.ItemFacture;
import com.gosuccour.entity.Itv;
import com.gosuccour.entity.Maintenance;
import com.gosuccour.entity.Plan;
import com.gosuccour.entity.Product;
import com.gosuccour.entity.Revision;
import com.gosuccour.entity.User;

public interface IClientService {
	
	/*Login*/
	public void saveUser(User user);
	
	public User findOneUser(Long id);
	
	public void deleteUser(Long id);
	
	public Client getClient(String username); 
	
	/*Client*/
	public List<Client>findAll();

	public Page<Client> findAll(Pageable pageable);
	
	public void save(Client client);
		
	public Client findOne(Long id);
	
	public void delete(Long id);
	
	/*Car*/	
	public void saveCar(Car car);
	
	public void deleteCar(Long id);
	
	public Car findOneCar(Long id);
	
	
	/*Facture*/
	
	public void saveFacture(Facture facture);
	
	public Facture finOneFacture(Long id);
	
	/*ItemFacture*/
	
	public void saveItemFacture(ItemFacture itemFacture);
	
	public ItemFacture findOneItemFacture(Long id);
	
	
	/*Maintenance*/
	
	public void saveMaintenance(Maintenance maintenance);
	
	public Maintenance findOneMaintenance(Long id);
	
	public List<Maintenance>finAllMaintenance();
	
	/*Revision*/
	
	public void saveRevision(Revision revision);
	
	public Revision findOneRevision(Long id);
	
	
	/*Product*/
	
	public List<Product>findAllProduct();
	
	/*Plan*/
	
	public List<Plan>findAllPlan();	
	
	/*Itv*/
	
	public void saveItv(Itv itv);
	

	/*Login Android*/
	public void addListToken(String token);
	
	public int checkLogin(String username,String password);
	
	/*Location*/
	public  void saveLocationClient(Coordenadas coordenadas);
	
}
