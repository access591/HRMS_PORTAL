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
		try {
			
			session.beginTransaction();
			Query<EmployeeRequisition> query = session.createQuery("from EmployeeRequisition "
					+ "e where e.status = 'N'",EmployeeRequisition.class);
			
			session.getTransaction().commit();
			return query.list();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return null;
	}
	
	
	@Override
	public List<EmployeeRequisition> findEmployeeReqByStatusY() {
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			Query<EmployeeRequisition> query = session.createQuery("from EmployeeRequisition e "
					+ "left join fetch e.departmet d where e.status=:status"
					+ " group by d",
					EmployeeRequisition.class);
			
			query.setParameter("status", "Y");
			
			session.getTransaction().commit();
			
			return query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}
	
	
	@Override
	public void approvedStatusByReqCode(String reqCode) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<EmployeeRequisition> query = session.createQuery("UPDATE EmployeeRequisition e set e.status =:status WHERE e.reqCode = :reqCode",EmployeeRequisition.class);
		query.setParameter("status", "Y");
		query.setParameter("reqCode", reqCode);
		Transaction tx = session.beginTransaction();
		query.executeUpdate();
		tx.commit();
		session.close();
		
		
	}
		
	

	
	
}
