package com.imcs.maven.hib.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.imcs.maven.hib.Util.HibernateUtils;
import com.imcs.maven.hib.pojo.Orders;

public class OrderDao implements IOrderDao {

	

	public void addOrder(Orders order) {
		Session session = getSession();
		Transaction transcation = session.beginTransaction();
		try {
			session.save(order);
			transcation.commit();
		} catch (Exception e) {
			transcation.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}

	public void deleteOrder(int id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Orders order = (Orders) getSession().get(Orders.class, id);
			session.delete(order);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}

	public void updateOrder(Orders order) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(order);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			if (session != null)
				session.close();
		}

	}

	public Orders getOrder(int id) {
		Session session = getSession();
		Orders order = (Orders) session.get(Orders.class, id);
		session.close();
		return order;
	}

	private Session getSession() {
		return HibernateUtils.getSessionFactory().openSession();
	}

	@Override
	public List<Orders> getOrdersByInvoiceRange(Date d1, Date d2) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(Orders.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.between("invoiceDate", d1, d2));
			
			@SuppressWarnings("unchecked")
			List<Orders> orders = criteria.list();
			return orders;

		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return null;
		}finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public List<Orders> getOrdersByDeliveryRange(Date d1, Date d2) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(Orders.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.between("deliverDate", d1, d2));
			@SuppressWarnings("unchecked")
			List<Orders> orders = criteria.list();
			return orders;

		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return null;
		}finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public List<Orders> getOrdersByTotalPrice(double price) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery("from Orders o where o.totalPrice >:totalPrice");
			query.setParameter("totalPrice", price);
			@SuppressWarnings("unchecked")
			List<Orders> orders = query.list();
			transaction.commit();
			return orders;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Orders> getOrdersByStatus(String status) {
		Session session=getSession();
		Transaction transaction=session.beginTransaction();
		try{
			Criteria criteria=session.createCriteria(Orders.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.add(Restrictions.like("status", status));
			
			@SuppressWarnings("unchecked")
			List<Orders> orders=criteria.list();
			transaction.commit();
			return orders;
		}
		catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Integer> getProductsByOrderId(int id) {
		Session session=getSession();
		Transaction transaction=session.beginTransaction();
		try{
			Query query=session.createSQLQuery("select product_id from customers.orders_products where order_id=:orderId");
			query.setParameter("orderId", id);
			@SuppressWarnings("unchecked")
			List<Integer> productIds=query.list();
			System.out.println(productIds);
			transaction.commit();
			return productIds;
		}
		catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
			return null;
		}
	}

}
