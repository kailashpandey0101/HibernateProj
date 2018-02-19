package com.imcs.maven.hib.pojo;

import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Access(AccessType.FIELD)
@Table(name = "customer")
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private long custId;

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param company
	 * @param street
	 * @param city
	 * @param state
	 * @param zipCode
	 */

	public Customer(String firstName, String lastName, String email, String company, String street, String city,
			String state, String zipCode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.company = company;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	/**
	 * 
	 */
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String email;

	private String company;
	private String street;
	private String city;
	private String state;

	@Column(name = "zip_code")
	private String zipCode;

	@OneToMany
	@JoinColumn(name = "customer_id")
	private Set<Orders> orders;

}
