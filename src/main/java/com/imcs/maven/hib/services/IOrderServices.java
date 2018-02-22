package com.imcs.maven.hib.services;

import java.util.Date;
import java.util.List;

import com.imcs.maven.hib.pojo.Orders;
import com.imcs.maven.hib.pojo.Products;

public interface IOrderServices {
List<Orders> getOrdersByInvoiceRange(Date d1,Date d2);
List<Orders> getOrdersByDeliveryRange(Date d1,Date d2);
List<Orders> getOrdersByTotalPrice(double price);
List<Orders> getOrdersByStatus(String status);
List<Integer> getProductsByOrderId(int id);
}
