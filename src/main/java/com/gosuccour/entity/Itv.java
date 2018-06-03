package com.gosuccour.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="itvs")
public class Itv implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double price;
	
	private String year;
	
	@Column(name = "identify", insertable=false, updatable = false, nullable = false, columnDefinition = "varchar(50) default 'itv'") 
	private  String identify="itv";
	
	private Long factura_id;

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

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getFactura_id() {
		return factura_id;
	}

	public void setFactura_id(Long factura_id) {
		this.factura_id = factura_id;
	}

	@Override
	public String toString() {
		return "* Service of: " +" "+ identify +" "+" "+ "You have contract for the:" +" "+ year;
	} 
	
	
	
	
}
