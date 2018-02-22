package com.imcs.maven.hib.services;

import java.util.Date;
import java.util.List;

import com.imcs.maven.hib.dao.IOrderDao;
import com.imcs.maven.hib.dao.OrderDao;
import com.imcs.maven.hib.pojo.Orders;
import com.imcs.maven.hib.pojo.Products;

public class OrderServices implements IOrderServices{
	IOrderDao orderDao=new OrderDao();
	@Override
	public List<Orders> getOrdersByInvoiceRange(Date d1, Date d2) {
		List<Orders>invoiceDates=orderDao.getOrdersByInvoiceRange(d1, d2);
		return invoiceDates;
	}
	@Override
	public List<Orders> getOrdersByDeliveryRange(Date d1, Date d2) {
		List<Orders>deliveryDates=orderDao.getOrdersByInvoiceRange(d1, d2);
		return deliveryDates;
	}
	

	@Override
	public List<Orders> getOrdersByTotalPrice(double price) {
		return orderDao.getOrdersByTotalPrice(price);
	}
	@Override
	public List<Orders> getOrdersByStatus(String status) {
		// TODO Auto-generated method stub
		return orderDao.getOrdersByStatus(status);
	}
	@Override
	public List<Integer> getProductsByOrderId(int id) {
		// TODO Auto-generated method stub
		return orderDao.getProductsByOrderId(id);
	}
	
}
