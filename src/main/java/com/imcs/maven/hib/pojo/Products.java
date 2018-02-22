package com.imcs.maven.hib.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Access(AccessType.FIELD)
@Table(name = "products")
@Getter @Setter
@ToString(exclude="ordersProducts")
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private Integer productId;

	private String name;

	private String description;

	private double price;

	@OneToMany(fetch=FetchType.EAGER,mappedBy = "products",cascade={CascadeType.ALL})
	private Set<OrdersProducts> ordersProducts=new HashSet<OrdersProducts>();

}
