package com.imcs.maven.hib.app;

import com.imcs.maven.hib.dao.CustomerDao;
import com.imcs.maven.hib.dao.ICustomerDao;
import com.imcs.maven.hib.pojo.Customer;

public class CustomerApp {

	public static void main(String[] args) {
		ICustomerDao customerDao = new CustomerDao();
		Customer customer = new Customer();
		customer.setFirstName("Kailash");
		customer.setLastName("Pandey");
		customer.setEmail("xyz@abc.com");
		
		//Address address=new Address();
		//customer.setAddress(address);

		customerDao.addCustomer(customer);

		// How to load this customer again here?

		// System.out.println(customerDao.loadCustomer(1));
	}

}
