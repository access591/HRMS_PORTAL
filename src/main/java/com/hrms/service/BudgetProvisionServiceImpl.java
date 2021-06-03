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

@Service
public class BudgetProvisionServiceImpl implements BudgetProvisionService {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void saveBudgetProvision(BudgetProvision budgetProvision) {

		System.out.println("session exist or not : " + sessionFactory.isOpen());
		try {
			Session session = sessionFactory.openSession();
			System.out.println("session exist or not : " + sessionFactory.isOpen());
			Transaction tx = session.beginTransaction();

			session.save(budgetProvision);
			tx.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("session exist or not : " + sessionFactory.isOpen());
	}

	@Override
	public List<BudgetProvision> getAllBudgetProvision() {
		try (Session session = sessionFactory.openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<BudgetProvision> criteria = builder.createQuery(BudgetProvision.class);
			criteria.from(BudgetProvision.class);
			List<BudgetProvision> entityList = session.createQuery(criteria).getResultList();

			return entityList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BudgetProvision findByBudgetProvisionId(Long budgitProvisionId) {

		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			BudgetProvision budgetProvision = session.find(BudgetProvision.class, budgitProvisionId);
			tx.commit();
			session.close();
			return budgetProvision;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateBudgetProvision(BudgetProvision budgetProvision) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			BudgetProvision b = session.find(BudgetProvision.class, budgetProvision.getBudgetProvisionId());

			b = (BudgetProvision) session.merge(budgetProvision);
			tx.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
