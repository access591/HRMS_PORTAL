package com.hrms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.hrms.model.Loan;

@Repository
public class LoanMaterDaoImpl implements LoanMaterDao {
	@Autowired
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(LoanMaterDaoImpl.class.getName());

	@Override
	public void addLoan(Loan loan) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.persist(loan);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("LoanMaterDaoImpl.addLoan" + e.getMessage());
		}

	}

	@Override
	public List<Loan> getAllLoans() {
		List<Loan> listLoan = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Loan.class);
			listLoan = (List<Loan>) criteria.setFetchMode("M_Loan", FetchMode.SELECT).list();

		} catch (Exception e) {
			logger.info("LoanMaterDaoImpl.getAllLoans" + e.getMessage());
		}
		return listLoan;
	}

	@Override
	public Loan findLoanById(String id) {
		Loan loanEdit = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Loan.class);
			loanEdit = (Loan) criteria.setFetchMode("M_Loan", FetchMode.SELECT).add(Restrictions.eq("Loan_Code", id))
					.uniqueResult();
		} catch (Exception e) {
			logger.info("LoanMaterDaoImpl.findLoanById" + e.getMessage());
		}
		return loanEdit;
	}

	@Override
	public void updateLoan(Loan L) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(L);
			tx.commit();
			session.close();

		} catch (Exception e) {
			logger.info("LoanMaterDaoImpl.updateLoan" + e.getMessage());
		}

	}

	@Override
	public void removeLoan(String id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			Loan l = (Loan) session.load(Loan.class, new String(id));
			session.delete(l);
			tx.commit();
			session.close();

		} catch (Exception e) {
			logger.info("LoanMaterDaoImpl.removeLoan" + e.getMessage());
		}

	}

}
