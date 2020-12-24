package com.hrms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.hrms.model.Loan;

@Repository
public class LoanMaterDaoImpl implements LoanMaterDao {
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public void addLoan(Loan loan) {
		  Session session = sessionFactory.openSession(); 
			 Transaction tx = session.beginTransaction();
			 session.persist(loan);
			 tx.commit();
			  session.close();
		
	}

	@Override
	public List<Loan> getAllLoans() {
		
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Loan.class);
		List<Loan> listLoan = (List<Loan>) criteria.setFetchMode("M_Loan", FetchMode.SELECT).list();
		return listLoan;
	}

	@Override
	public Loan findLoanById(String id) {
		
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Loan.class);
		Loan loanEdit = (Loan) criteria.setFetchMode("M_Loan", FetchMode.SELECT)
				.add(Restrictions.eq("Loan_Code",id)).uniqueResult();

		return loanEdit;
	}

	@Override
	public void updateLoan(Loan L) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(L);
		tx.commit();
		session.close();
		
	}

	@Override
	public void removeLoan(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Loan l = (Loan) session.load(Loan.class, new String(id));
		System.out.println("value of G " + l);

		session.delete(l);
		tx.commit();
		session.close();	
		
	}
	

}
