package com.imcs.maven.hib.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="address")
@Getter @Setter
@ToString(exclude="customer")
public class Address {
	@Id
	@GeneratedValue
	@Column(name="address_id")
	private long addressId;
	
	private String street;
	private String city;
	private String state;
	
	@Column(name="zip_code")
	private String zipCode;
	
	@OneToOne(mappedBy="address")
	private Customer customer;

	
	
}
