package com.gosuccour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gosuccour.dao.ICarDao;
import com.gosuccour.dao.IClientDao;
import com.gosuccour.dao.ICoordenadasDao;
import com.gosuccour.dao.IFactureDao;
import com.gosuccour.dao.IItemFactureDao;
import com.gosuccour.dao.IItv;
import com.gosuccour.dao.IMaintenanceDao;
import com.gosuccour.dao.IPlanDao;
import com.gosuccour.dao.IProductDao;
import com.gosuccour.dao.IRevisionDao;
import com.gosuccour.dao.IUserDao;
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

@Service
public class ClientServImpl implements IClientService {

	@Autowired
	private IClientDao clientDao;

	@Autowired
	private IUserDao userDao;

	@Autowired
	private ICarDao carDao;
	
	@Autowired
	private IProductDao productDao;
	
	@Autowired
	private IMaintenanceDao maintenanceDao;
	
	@Autowired
	private IRevisionDao revisionDao;
	
	@Autowired
	private IItv itvDao;
	
	@Autowired
	private IPlanDao planDao;
	
	@Autowired
	private IFactureDao factureDao;
	
	@Autowired
	private IItemFactureDao itemFactureDao;
	
	@Autowired
	private ICoordenadasDao coordenadasDao;

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return (List<Client>) clientDao.findAll();
	}

	@Override
	@Transactional // solo escritura
	public void save(Client client) {
		clientDao.save(client);
	}

	@Override
	@Transactional(readOnly = true)
	public Client findOne(Long id) {
		return clientDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clientDao.deleteById(id);
	}

	@Override
	public Page<Client> findAll(Pageable pageable) {
		return clientDao.findAll(pageable);
	}

	/* User */
	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

	@Override
	public User findOneUser(Long id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	public void deleteUser(Long id) {
		userDao.deleteById(id);
	}

	/* Login */
	@Override
	public Client getClient(String username) {
		User user = userDao.findByUsername(username);
		Long id = user.getClientId();
		return clientDao.findById(id).orElse(null);
	}

	@Override
	public void addListToken(String token) {
		// TODO Auto-generated method stub
	}

	@Override
	public int checkLogin(String username, String password) {
		int check;
		User user = userDao.findByUsername(username);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				check = 1;
			} else {
				check = 2;
			}
		} else {
			check = 3;
		}
		return check;
	}

	/* Car */
	@Override
	@Transactional
	public void saveCar(Car car) {
		carDao.save(car);
	}

	@Override
	public void deleteCar(Long id) {
		carDao.deleteById(id);
	}

	@Override
	public Car findOneCar(Long id) {
		return carDao.findById(id).orElse(null);
	}
	
	/*Product*/
	@Override
	public List<Product> findAllProduct() {
		return (List<Product>) productDao.findAll();
	}
	

	/*Facture*/
	@Override
	public void saveFacture(Facture facture) {
		factureDao.save(facture);
	}

	@Override
	public Facture finOneFacture(Long id) {
		// TODO Auto-generated method stub
		return factureDao.findById(id).orElse(null);
	}
	
	/*ItemFacture*/
	@Override
	public void saveItemFacture(ItemFacture itemFacture) {
		itemFactureDao.save(itemFacture);
	}

	@Override
	public ItemFacture findOneItemFacture(Long id) {
		return itemFactureDao.findById(id).orElse(null);
	}
	
	/*Maintenance*/
	@Override
	public List<Maintenance> finAllMaintenance() {
	return (List<Maintenance>) maintenanceDao.findAll();
	}

	@Override
	public void saveMaintenance(Maintenance maintenance) {
		maintenanceDao.save(maintenance);		
	}

	@Override
	public Maintenance findOneMaintenance(Long id) {		
		return maintenanceDao.findById(id).orElse(null);
	}
		
	/*Revision*/

	@Override
	public Revision findOneRevision(Long id) {
		return revisionDao.findById(id).orElse(null);
	}

	@Override
	public void saveRevision(Revision revision) {
		revisionDao.save(revision);
	}
	
	/*Plan*/
	@Override
	public List<Plan> findAllPlan() {
		return (List<Plan>) planDao.findAll();
	}

	/*Itv*/
	@Override
	public void saveItv(Itv itv) {
		itvDao.save(itv);
	}

	/*Location*/
	@Override
	public void saveLocationClient(Coordenadas coordenadas) {
		coordenadasDao.save(coordenadas);
	}
	
}// end class
