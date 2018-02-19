package com.imcs.maven.hib.app;

import com.imcs.maven.hib.dao.CustomerDao;
import com.imcs.maven.hib.dao.ICustomerDao;
import com.imcs.maven.hib.pojo.Customer;

public class CustomerApp {

	public static void main(String[] args) {
		ICustomerDao customerDao = new CustomerDao();
		Customer customer = new Customer("Kailash", "Pandey", "abc@xy.com", "imcs", "123", "dallas", "texas", "70987");

		customerDao.addCustomer(customer);

		// How to load this customer again here?

		// System.out.println(customerDao.loadCustomer(1));
	}

}
