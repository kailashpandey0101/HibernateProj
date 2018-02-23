package com.imcs.maven.hib.pojo;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Access(AccessType.FIELD)
@Table(name = "orders")
@Getter @Setter
@ToString(exclude="ordersProducts")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Integer orderId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@Temporal(TemporalType.DATE)
	@Column(name = "invoice_date")
	private Date invoiceDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "deliver_date")
	private Date deliverDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "payement_date")
	private Date paymentDate;
	
	@Column(name="total_price")
	private double totalPrice;

	private String status;

	@OneToMany(fetch=FetchType.EAGER,mappedBy = "orders", cascade={CascadeType.ALL})
	private Set<OrdersProducts> ordersProducts=new HashSet<OrdersProducts>();



}
