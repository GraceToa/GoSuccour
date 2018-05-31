package com.gosuccour.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "maintenances")
public class Maintenance implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="maintenance_id")
	private Long id;

	private Double price;
	
	@Column(name = "identify", insertable=false, updatable = false, nullable = false, columnDefinition = "varchar(50) default 'maintenance'") 
	private  String identify="maintenance"; 
	
	@NotEmpty
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="maintenance_product",joinColumns= {@JoinColumn(name="maintenance_id")},inverseJoinColumns= {@JoinColumn(name="product_id")})
	private List<Product> listProducts;
	
	

	public Maintenance() {
		listProducts = new ArrayList<Product>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Product> getListProducts() {
		return listProducts;
	}

	public void setListProducts(List<Product> listProducts) {
		this.listProducts = listProducts;
	}

	public void addListProducts(Product product) {
		listProducts.add(product);
	}
	
	public Double getTotalMaintenance(Double priceProduct) {
		return price+priceProduct;
		
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}
	
	
}
