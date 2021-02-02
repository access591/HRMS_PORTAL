package com.hrms.repository;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.hrms.model.Bank;


@Repository
public class BankDaoImpl implements BankDao {
	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(BankDaoImpl.class.getName());

	@Override
	public void addBank(Bank bank) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.persist(bank);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public List<Bank> getAllBanks() {
		List<Bank> listBank = null;

		try {
			Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Bank.class);
			listBank = (List<Bank>) criteria.setFetchMode("M_BANK", FetchMode.SELECT).list();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return listBank;
	}

	@Override
	public Bank findBankById(String id) {
		Bank bankEdit = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Bank.class);
			bankEdit = (Bank) criteria.setFetchMode("M_BANK", FetchMode.SELECT).add(Restrictions.eq("Bank_Code", id))
					.uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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
			logger.error(e.getMessage(), e);
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
			logger.error(e.getMessage(), e);
		}

	}

}
