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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;



/**
 * @Table(name="clients")//si la tabla es igual que la clase se omite esta linea	
 * pero en base de datos las tablas son el plural, en los atributos se utiliza 
 * @Coumn si los nombres son diferentes en las columnas de bd por eje Date
 * en bd por omision se utiliza guion bajo
 * @Temporal formatear fecha a bd
 * @author GraceToa
 *
 */
@Entity
@Table(name="clients")					
public class Client implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String surname;
	@NotEmpty
	private String lastname;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	private String phone;
	@NotEmpty
	private String address;
	@NotEmpty
	private String country;
	@NotEmpty
	private String city;
	@NotEmpty
	private String cp;
	@NotNull
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createAt;
	
	private String photo;
	
	@OneToMany(mappedBy="client",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Car>listCars;

	
	//iniciamos los arrayList
	public Client() {
		
		listCars = new ArrayList<Car>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public List<Car> getListCars() {
		return listCars;
	}

	public void setListCars(List<Car> listCars) {
		this.listCars = listCars;
	}
	public void addCars(Car car) {
		listCars.add(car);
	}

	@Override
	public String toString() {
		return  surname + " " + lastname ;
	}
	

}
