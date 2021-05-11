package com.hrms.repository;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.EmployeeRequisition;

@Repository
public class EmployeeRequisitionDaoImpl extends AbstractGenericDao<EmployeeRequisition> implements EmployeeRequisitionDao{

	@Autowired SessionFactory sessionFactory;
	@Override
	public List<EmployeeRequisition> findEmployeeReqByStatusN() {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from EmployeeRequisition e where e.status = 'N'");
		List<EmployeeRequisition> listEmployeeReq = query.list();
		//session.getTransaction().commit();
		return listEmployeeReq;
	}
	
	
	@Override
	public List<EmployeeRequisition> findEmployeeReqByStatusY() {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from EmployeeRequisition e where e.status = 'Y'");
		List<EmployeeRequisition> listEmployeeReq = query.list();
		//session.getTransaction().commit();
		return listEmployeeReq;
	}
	
	
	@Override
	//@Transactional
    //@Modifying
	public void approvedStatusByReqCode(String reqCode) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE EmployeeRequisition e set e.status =:status WHERE e.reqCode = :reqCode" );
		query.setParameter("status", "Y");
		query.setParameter("reqCode", reqCode);
		Transaction tx = session.beginTransaction();
		int result = query.executeUpdate();
		tx.commit();
		session.close();
		System.out.println("result : "+ result);
		//return listEmployeeReq;
	}
		
	

	
	
}
