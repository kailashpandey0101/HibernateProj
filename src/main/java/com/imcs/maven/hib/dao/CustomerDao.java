package com.imcs.maven.hib.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.imcs.maven.hib.Util.HibernateUtils;
import com.imcs.maven.hib.pojo.Customer;
import com.imcs.maven.hib.pojo.Products;

public class CustomerDao implements ICustomerDao {

	public Session saveObject(Customer customer) {
		Session session = getSession();
		session.save(customer);
		return session;
	}
	public void addCustomer(Customer customer) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(customer);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}

	public Customer loadCustomer(long id) {
		Session session = getSession();
		Customer customer = (Customer) session.get(Customer.class, id);
		session.close();
		return customer;

	}

	public void updateCustomer(Customer customer) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(customer);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}

	public void deleteCustomer(long id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Customer customer = (Customer) getSession().get(Customer.class, id);
			session.delete(customer);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}


	private Session getSession() {
		return HibernateUtils.getSessionFactory().openSession();
	}

}
