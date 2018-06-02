package com.gosuccour.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="revisions")
public class Revision implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="revision_id")
	private Long id;
	
	@Column(name = "identify", insertable=false, updatable = false, nullable = false, columnDefinition = "varchar(50) default 'revision'") 
	final  String identify="revision"; 
	
	private Double price;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="revision_plan",joinColumns= {@JoinColumn(name="revision_id")},inverseJoinColumns= {@JoinColumn(name="plan_id")})
	private List<Plan>listPlan;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="revision_product",joinColumns= {@JoinColumn(name="revision_id")},inverseJoinColumns= {@JoinColumn(name="product_id")})
	private List<Product> listProducts;
	
	private Long factura_id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentify() {
		return identify;
	}

	public List<Product> getListProducts() {
		return listProducts;
	}

	public void setListProducts(List<Product> listProducts) {
		this.listProducts = listProducts;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Plan> getListPlan() {
		return listPlan;
	}

	public void setListPlan(List<Plan> listPlan) {
		this.listPlan = listPlan;
	}
	
	public Double totalPriceRevision(double porcentaje,double price) {
		Double porcentajePrice= price*porcentaje;
		Double total=price-porcentajePrice;
		System.out.println(total);
		return total;
		
	}

	public Long getFactura_id() {
		return factura_id;
	}

	public void setFactura_id(Long factura_id) {
		this.factura_id = factura_id;
	}

	@Override
	public String toString() {
		return "* Service of:  "+" " + identify + ", Price:" + " "+price +" "+ ", Your Plan of:" + listPlan.toString()
				+ ",Product:" + listProducts ;
	}

	
	 
}
