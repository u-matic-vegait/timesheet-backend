package rs.vegait.sigma.timesheet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	@Column
	private String address;

	@Column
	private String city;

	@Column
	private Integer zipcode;

	@Column
	private String country;

	public Client() {
		super();
	}

	public Client(Long id, String name, String address, String city, Integer zipCode, String country) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.zipcode = zipCode;
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getZipCode() {
		return zipcode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipcode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
