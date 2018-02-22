package com.imcs.maven.hib.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.imcs.maven.hib.pojo.Orders;
import com.imcs.maven.hib.pojo.Products;

public interface IOrderDao {
	
	void addOrder(Orders order);
	void deleteOrder(int id);
	void updateOrder(Orders order);
	Orders getOrder(int id);
	
	List<Orders> getOrdersByInvoiceRange(Date d1,Date d2);
	List<Orders> getOrdersByDeliveryRange(Date d1,Date d2);
	
	List<Orders> getOrdersByTotalPrice(double price);
	List<Orders> getOrdersByStatus(String status);
	
	List<Integer> getProductsByOrderId(int id);
}
