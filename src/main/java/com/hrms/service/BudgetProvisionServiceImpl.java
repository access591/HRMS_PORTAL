package com.hrms.service;

import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.BudgetProvision;

@Service
public class BudgetProvisionServiceImpl implements BudgetProvisionService {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void saveBudgetProvision(BudgetProvision budgetProvision) {

		Session session = sessionFactory.openSession();
		try {
			
			Transaction tx = session.beginTransaction();

			session.save(budgetProvision);
			tx.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	@Override
	public List<BudgetProvision> getAllBudgetProvision() {
		
		Session session = sessionFactory.openSession();
		try  {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<BudgetProvision> criteria = builder.createQuery(BudgetProvision.class);
			criteria.from(BudgetProvision.class);
			
			return session.createQuery(criteria).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			session.close();
		}
		return Collections.emptyList();
	}

	@Override
	public BudgetProvision findByBudgetProvisionId(Long budgitProvisionId) {

		Session session = sessionFactory.openSession();
		try {
			
			Transaction tx = session.beginTransaction();
			BudgetProvision budgetProvision = session.find(BudgetProvision.class, budgitProvisionId);
			tx.commit();
			session.close();
			return budgetProvision;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public void updateBudgetProvision(BudgetProvision budgetProvision) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			
			tx = session.beginTransaction();
			session.find(BudgetProvision.class, budgetProvision.getBudgetProvisionId());

			session.merge(budgetProvision);
			tx.commit();
			
		} catch (Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	@Override
	public void removeBudgetProvision(Long budgetId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Object o = session.get(BudgetProvision.class, budgetId);
		BudgetProvision e = (BudgetProvision) o;
		
		session.delete(e);
		tx.commit();
		session.close();
		
	}

}
