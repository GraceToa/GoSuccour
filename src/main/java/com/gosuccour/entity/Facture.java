package com.gosuccour.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "factures")
public class Facture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	private Date createAt;

	private Double price;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	private Car car;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "facture_id") // clave foranea se generará automaticamente en la tabla factures_items
	private List<ItemFacture> items;

	public Facture() {
		this.items = new ArrayList<ItemFacture>();
	}

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public List<ItemFacture> getItems() {
		return items;
	}

	public void setItems(List<ItemFacture> items) {
		this.items = items;
	}

	public void addItemFacture(ItemFacture item) {
		this.items.add(item);
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		price =getTotal();
		this.price = price;
	}

	public Double getTotal() {
		Double total = 0.0;
		int size = items.size();
		for (int i = 0; i < size; i++) {
			total += items.get(i).getPrice();
		}
		
		return total;
	}

	// @PrePersist persiste el registro en la bd, no es necesario tener la fecha en
	// el
	// formulario
	// ya que es un fecha interna de creación

}
