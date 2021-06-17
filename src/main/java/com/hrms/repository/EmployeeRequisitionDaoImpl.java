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
		
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<EmployeeRequisition> query = session.createQuery("from EmployeeRequisition "
					+ "e where e.status = 'N'",EmployeeRequisition.class);
			List<EmployeeRequisition> listEmployeeReq = query.list();
			session.close();
			return listEmployeeReq;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//session.getTransaction().commit();
		return null;
	}
	
	
	@Override
	public List<EmployeeRequisition> findEmployeeReqByStatusY() {
		
		try {
			Session session = sessionFactory.getCurrentSession();
//			Query<EmployeeRequisition> query = session.createQuery("from EmployeeRequisition e "
//					+ "inner join fetch e.departmet d"
//					+ " group by d.deptCode where e.status = 'Y'",
//					EmployeeRequisition.class);
			
			Query<EmployeeRequisition> query = session.createQuery("from EmployeeRequisition e "
					+ "left join fetch e.departmet d where e.status=:status"
					+ " group by d",
					EmployeeRequisition.class);
			
			query.setParameter("status", "Y");
			List<EmployeeRequisition> listEmployeeReq = query.getResultList();
			//session.getTransaction().commit();
			System.out.println("employee requisition size====>"+listEmployeeReq.size());
			//session.close();
			return listEmployeeReq;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
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
