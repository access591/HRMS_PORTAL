package com.hrms.repository;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private Logger logger = LoggerFactory.getLogger(BankDaoImpl.class.getName());

	@Override
	public void addBank(Bank bank) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.persist(bank);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("BankDaoImpl.addBank" + e.getMessage());
		}

	}

	@Override
	public List<Bank> getAllBanks() {
		List<Bank> listBank = null;

		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Bank.class);
			listBank = (List<Bank>) criteria.setFetchMode("M_BANK", FetchMode.SELECT).list();

		} catch (Exception e) {
			logger.info("BankDaoImpl.getAllBanks" + e.getMessage());
		}
		return listBank;
	}

	@Override
	public Bank findBankById(String id) {
		Bank bankEdit = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Bank.class);
			bankEdit = (Bank) criteria.setFetchMode("M_BANK", FetchMode.SELECT).add(Restrictions.eq("Bank_Code", id))
					.uniqueResult();
		} catch (Exception e) {
			logger.info("BankDaoImpl.findBankById" + e.getMessage());
		}

		return bankEdit;

	}

	@Override
	public void updateBank(Bank d) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(d);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("BankDaoImpl.updateBank" + e.getMessage());
		}

	}

	@Override
	public void removeBank(String id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			Bank p = (Bank) session.load(Bank.class, new String(id));
			session.delete(p);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("BankDaoImpl.removeBank" + e.getMessage());
		}

	}

}
