package com.imcs.maven.hib.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.imcs.maven.hib.Util.HibernateUtils;
import com.imcs.maven.hib.pojo.Customer;
import com.imcs.maven.hib.pojo.Orders;
import com.imcs.maven.hib.pojo.Products;

public class ProductsDao implements IProductsDao {


	public void addProducts(Products products) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(products);
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

	public List<Products> getProducts() {
		Session session=getSession();
		Query query=session.createQuery("from Products");
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return query.list();
		
	}

	public void updateProducts(Products products) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(products);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}

	@Override
	public Products getProduct(Integer id) {
		Session session = getSession();
		Products product = (Products) session.get(Products.class, id);
		session.close();
		return product;
	}





}
