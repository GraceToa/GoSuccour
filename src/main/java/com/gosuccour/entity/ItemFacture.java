package com.gosuccour.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "factures_items")
public class ItemFacture implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long facture_id;

	private Double price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maintenance_id")
	private Maintenance maintenance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "revision_id")
	private Revision revision;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "itv_id")
	private Itv itv;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Maintenance getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(Maintenance maintenance) {
		this.maintenance = maintenance;
	}

	public Long getFacture_id() {
		return facture_id;
	}

	public void setFacture_id(Long facture_id) {
		this.facture_id = facture_id;
	}

	public Revision getRevision() {
		return revision;
	}

	public void setRevision(Revision revision) {
		this.revision = revision;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	

	public Itv getItv() {
		return itv;
	}

	public void setItv(Itv itv) {
		this.itv = itv;
	}

	public Double calculateImport() {
		Double total = maintenance.getPrice() + revision.getPrice();
		return total;
	}
}
