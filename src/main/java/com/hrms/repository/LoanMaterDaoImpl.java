package com.hrms.repository;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import com.hrms.model.Loan;

@Repository
public class LoanMaterDaoImpl implements LoanMaterDao {
	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(LoanMaterDaoImpl.class.getName());

	@Override
	public void addLoan(Loan loan) {
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.persist(loan);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public List<Loan> getAllLoans() {
		List<Loan> listLoan = null;
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Loan.class);
			listLoan = (List<Loan>) criteria.setFetchMode("M_Loan", FetchMode.SELECT).list();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return listLoan;
	}

	@Override
	public Loan findLoanById(String id) {
		Loan loanEdit = null;
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Loan.class);
			loanEdit = (Loan) criteria.setFetchMode("M_Loan", FetchMode.SELECT).add(Restrictions.eq("Loan_Code", id))
					.uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return loanEdit;
	}

	@Override
	public void updateLoan(Loan L) {
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(L);
			tx.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public void removeLoan(String id) {
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			Loan l = (Loan) session.load(Loan.class, new String(id));
			session.delete(l);
			tx.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
