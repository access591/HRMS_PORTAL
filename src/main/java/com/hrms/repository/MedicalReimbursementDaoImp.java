package com.hrms.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.MedicalReimbursement;

@Repository
public class MedicalReimbursementDaoImp  extends AbstractGenericDao<MedicalReimbursement> implements MedicalReimbursementDao{
	@Autowired SessionFactory sessionFactory;
	@Override
	public void approvedByMrId(String slipNo) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE MedicalReimbursement e set e.approvalStatus =:approvalStatus WHERE e.slipNo = :slipNo" );
		query.setParameter("approvalStatus", "Y");
		query.setParameter("slipNo", slipNo);
		Transaction tx = session.beginTransaction();
		int result = query.executeUpdate();
		tx.commit();
		session.close();
		System.out.println("result : "+ result);
		
	}

}
