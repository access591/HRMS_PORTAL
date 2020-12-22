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


import com.hrms.model.MiscAllowance;
@Repository
public class MiscAllowanceDeductionDaoImpl implements MiscAllowanceDeductionDao {
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public void addMiscAllowanceDeduction(MiscAllowance miscAllowance) {
		
		 Session session = sessionFactory.openSession(); 
		 Transaction tx = session.beginTransaction();
		 session.persist(miscAllowance);
		 tx.commit();
		 session.close();
		
	}

	@Override
	public List<MiscAllowance> getAllMiscAllowanceDeduction() {
	
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(MiscAllowance.class);
		List<MiscAllowance> listMiscAllowanceDeduction = (List<MiscAllowance>) criteria.setFetchMode("Allowance_Code", FetchMode.SELECT).list();
		return listMiscAllowanceDeduction;
	}

	@Override
	public MiscAllowance findMiscAllowanceDeductionById(String id) {
	
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(MiscAllowance.class);
		MiscAllowance editMiscAllowance = (MiscAllowance) criteria.setFetchMode("Allowance_Code", FetchMode.SELECT)
				.add(Restrictions.eq("Allowance_Code", id)).uniqueResult();

		return editMiscAllowance;
	}

	@Override
	public void updateMiscAllowanceDeduction(MiscAllowance M) {
	
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(M);
		tx.commit();
		session.close();	
		
	}

	@Override
	public void removeMiscAllowanceDeduction(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		MiscAllowance m = (MiscAllowance) session.load(MiscAllowance.class, new String(id));
	

		session.delete(m);
		tx.commit();
		session.close();
		
	}

}
