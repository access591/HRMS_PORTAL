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

import com.hrms.model.Bank;


@Repository
public class BankDaoImpl implements BankDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addBank(Bank bank) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(bank);
		tx.commit();
		session.close();

	}

	@Override
	public List<Bank> getAllBanks() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Bank.class);
		List<Bank> listBank = (List<Bank>) criteria.setFetchMode("M_BANK", FetchMode.SELECT).list();
		return listBank;
	}
	@Override
	public Bank findBankById(String id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Bank.class);
		Bank bankEdit = (Bank) criteria.setFetchMode("M_BANK", FetchMode.SELECT)
				.add(Restrictions.eq("Bank_Code", id)).uniqueResult();

		return bankEdit;

	}

	@Override
	public void updateBank(Bank d) {

		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(d);
		tx.commit();
		session.close();

	}

	@Override
	public void removeBank(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Bank p = (Bank) session.load(Bank.class, new String(id));
		System.out.println("value of p " + p);

		session.delete(p);
		tx.commit();
		session.close();

	}

	
}
