package com.imcs.maven.hib.pojo;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Entity
@Access(AccessType.FIELD)
@Table(name = "order_products")
@Data
public class OrdersProducts {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_product_id")
	private int orderProductId;

	private int quantity;

}
