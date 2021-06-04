package com.hrms.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.BudgetProvision;
import com.hrms.model.EmployeeRequisition;
import com.hrms.model.OrderIssueTracking;

@Service
public class OrderIssueTrackingServiceImpl implements OrderIssueTrackingService{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void saveOrderIssueTracking(OrderIssueTracking orderIssueTracking) {
		try {
			Session session = sessionFactory.openSession();
			System.out.println("session exist or not : " + sessionFactory.isOpen());
			Transaction tx = session.beginTransaction();

			session.save(orderIssueTracking);
			tx.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<OrderIssueTracking> getAllOrderIssueTracking() {
		try (Session session = sessionFactory.openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<OrderIssueTracking> criteria = builder.createQuery(OrderIssueTracking.class);
			criteria.from(OrderIssueTracking.class);
			List<OrderIssueTracking> entityList = session.createQuery(criteria).getResultList();

			return entityList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public OrderIssueTracking findOrderIssueTrackingById(Long orderIssueTrackingId) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			OrderIssueTracking orderIssueTracking = session.find(OrderIssueTracking.class, orderIssueTrackingId);
			tx.commit();
			session.close();
			return orderIssueTracking;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateOrderIssueTracking(OrderIssueTracking orderIssueTracking) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			OrderIssueTracking b = session.find(OrderIssueTracking.class, orderIssueTracking.getOrderTrackingId());

			b = (OrderIssueTracking) session.merge(orderIssueTracking);
			tx.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeOrderIssueTracking(Long orderTrackinId) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Object o = session.get(OrderIssueTracking.class, orderTrackinId);
			OrderIssueTracking e = (OrderIssueTracking) o;
			
			session.delete(e);
			tx.commit();
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
