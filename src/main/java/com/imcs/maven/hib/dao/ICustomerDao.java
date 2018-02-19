package com.imcs.maven.hib.dao;

import com.imcs.maven.hib.pojo.Customer;

public interface ICustomerDao {
	void addCustomer(Customer customer);
	Customer loadCustomer(long id);
	void updateCustomer(Customer customer);
	void deleteCustomer(long id);
	
}
