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


import com.hrms.model.MiscAllowance;
@Repository
public class MiscAllowanceDeductionDaoImpl implements MiscAllowanceDeductionDao {

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(MiscAllowanceDeductionDaoImpl.class.getName());

	@Override
	public void addMiscAllowanceDeduction(MiscAllowance miscAllowance) {
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.persist(miscAllowance);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public List<MiscAllowance> getAllMiscAllowanceDeduction() {
		List<MiscAllowance> listMiscAllowanceDeduction = null;
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(MiscAllowance.class);
			listMiscAllowanceDeduction = (List<MiscAllowance>) criteria.setFetchMode("Allowance_Code", FetchMode.SELECT)
					.list();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}

		return listMiscAllowanceDeduction;
	}

	@Override
	public MiscAllowance findMiscAllowanceDeductionById(String id) {
		MiscAllowance editMiscAllowance = null;
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(MiscAllowance.class);
			editMiscAllowance = (MiscAllowance) criteria.setFetchMode("Allowance_Code", FetchMode.SELECT)
					.add(Restrictions.eq("Allowance_Code", id)).uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}

		return editMiscAllowance;
	}

	@Override
	public void updateMiscAllowanceDeduction(MiscAllowance M) {
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(M);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}

	}

	@Override
	public void removeMiscAllowanceDeduction(String id) {
		try {

			final Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			MiscAllowance m = (MiscAllowance) session.load(MiscAllowance.class, new String(id));
			session.delete(m);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
