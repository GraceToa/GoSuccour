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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cars")
@JsonIgnoreProperties(value = { "client" })
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String model;

	@NotEmpty
	private String matriculation;

	@NotEmpty
	private String pneumatic;

	@NotEmpty
	@Column(name = "type_motor")
	private String typeMotor;

	@NotEmpty
	private String displacement;

	@ManyToOne(fetch = FetchType.LAZY)
	private Client client;
	
	@JsonBackReference
	@OneToMany(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Facture> listFactures;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMatriculation() {
		return matriculation;
	}

	public void setMatriculation(String matriculation) {
		this.matriculation = matriculation;
	}

	public String getPneumatic() {
		return pneumatic;
	}

	public void setPneumatic(String pneumatic) {
		this.pneumatic = pneumatic;
	}

	public String getTypeMotor() {
		return typeMotor;
	}

	public void setTypeMotor(String typeMotor) {
		this.typeMotor = typeMotor;
	}

	public String getDisplacement() {
		return displacement;
	}

	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Facture> getListFactures() {
		return listFactures;
	}

	public void setListFactures(List<Facture> listFactures) {
		this.listFactures = listFactures;
	}

	public void addFactures(Facture facture) {
		listFactures.add(facture);
	}

	public static int calculItvYear(String matriculation) {
		int numYear = 0;
		String sSubCadena = matriculation.substring(4, 7);

		switch (sSubCadena) {
		/* 2013 */
		case "HNT":
		case "HPC":
		case "HPN":
		case "HPY":
		case "HRK":
		case "HRX":
		case "HSK":
		case "HSR":
		case "HSZ":
		case "HTK":
		case "HTV":
		case "HVF":
			numYear = resultITV(0, 6);

			break;
		/* 2014 */
		case "HVN":
		case "HVZ":
		case "HWM":
		case "HXB":
		case "HXN":
		case "HYD":
		case "HYT":
		case "HZB":
		case "HZL":
		case "HZZ":
		case "JBL":
		case "JBY":
			numYear = resultITV(1, 6);
			break;

		/* 2015 */
		case "JCK":
		case "JCY":
		case "JDR":
		case "JFG":
		case "JFW":
		case "JPG":
		case "JHJ":
		case "JHT":
		case "JJH":
		case "JJW":
		case "JKK":
		case "JKZ":
			numYear = resultITV(2, 6);
			break;
		/* 2016 */
		case "JLN":
		case "JMF":
		case "JMY":
		case "JNR":
		case "JPK":
		case "JRG":
		case "JRZ":
		case "JSL":
		case "JTB":
		case "JTR":
		case "JVH":
		case "JVZ":
			numYear = resultITV(3, 6);
			break;
		/* 2017 */
		case "JWN":
		case "JXF":
		case "JYB":
		case "JYT":
		case "JZP":
		case "KBM":
		case "KCH":
		case "KCV":
		case "KDK":
		case "KFB":
		case "KFV":
		case "KGN":
			numYear = resultITV(4, 6);
			break;
		/* 2018 */
		case "KHF":
		case "KHX":
		case "KJV":
		case "KKN":
		case "KLH":
			numYear = resultITV(5, 6);
			break;
		default:
			break;

		}

		return numYear;
	}

	/**
	 * 
	 * @param añoTablaMatriculacion
	 * @param numeroPeriodicidadASumar
	 * @return Metodo que devuelve un numero 'Integer' con la fehca de la ITV
	 */
	public static int resultITV(int añoTablaMatriculacion, int numeroPeriodicidadASumar) {
		/* Tabla que contiene los años de las matriculas */
		ArrayList<String> years = new ArrayList<String>();
		int dateITV;
		years.add("2013");
		years.add("2014");
		years.add("2015");
		years.add("2016");
		years.add("2017");
		years.add("2018");
		dateITV = (Integer.valueOf(years.get(añoTablaMatriculacion)) + numeroPeriodicidadASumar);
		return dateITV;
	}

	// @PrePersist persiste el registro en la bd, no es necesario tener la fecha en
	// el
	// formulario
	// ya que es un fecha interna de creación

}
