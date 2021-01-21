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


import com.hrms.model.MiscAllowance;
@Repository
public class MiscAllowanceDeductionDaoImpl implements MiscAllowanceDeductionDao {

	@Autowired
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(MiscAllowanceDeductionDaoImpl.class.getName());

	@Override
	public void addMiscAllowanceDeduction(MiscAllowance miscAllowance) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.persist(miscAllowance);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("MiscAllowanceDeductionDaoImpl.addMiscAllowanceDeduction" + e.getMessage());
		}

	}

	@Override
	public List<MiscAllowance> getAllMiscAllowanceDeduction() {
		List<MiscAllowance> listMiscAllowanceDeduction = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(MiscAllowance.class);
			listMiscAllowanceDeduction = (List<MiscAllowance>) criteria.setFetchMode("Allowance_Code", FetchMode.SELECT)
					.list();

		} catch (Exception e) {
			logger.info("MiscAllowanceDeductionDaoImpl.getAllMiscAllowanceDeduction" + e.getMessage());

		}

		return listMiscAllowanceDeduction;
	}

	@Override
	public MiscAllowance findMiscAllowanceDeductionById(String id) {
		MiscAllowance editMiscAllowance = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(MiscAllowance.class);
			editMiscAllowance = (MiscAllowance) criteria.setFetchMode("Allowance_Code", FetchMode.SELECT)
					.add(Restrictions.eq("Allowance_Code", id)).uniqueResult();

		} catch (Exception e) {
			logger.info("MiscAllowanceDeductionDaoImpl.findMiscAllowanceDeductionById" + e.getMessage());

		}

		return editMiscAllowance;
	}

	@Override
	public void updateMiscAllowanceDeduction(MiscAllowance M) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(M);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("MiscAllowanceDeductionDaoImpl.updateMiscAllowanceDeduction" + e.getMessage());

		}

	}

	@Override
	public void removeMiscAllowanceDeduction(String id) {
		try {

			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			MiscAllowance m = (MiscAllowance) session.load(MiscAllowance.class, new String(id));
			session.delete(m);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("MiscAllowanceDeductionDaoImpl.removeMiscAllowanceDeduction" + e.getMessage());
		}
	}

}
