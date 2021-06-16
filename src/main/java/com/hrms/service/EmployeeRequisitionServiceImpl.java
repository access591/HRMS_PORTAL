package com.hrms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.EmployeeRequisition;
import com.hrms.repository.EmployeeRequisitionDao;

@Service
public class EmployeeRequisitionServiceImpl implements EmployeeRequisitionService{

	@Autowired EmployeeRequisitionDao employeRequisitionDao;
	@Autowired SessionFactory sessionFactory;
	
	
	@Override
	public void addEmployeeRequisition(EmployeeRequisition employeReq) {
		Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    employeReq.setReqCode(employeRequisitionDao.getMaxId("REQ"));
	    session.save(employeReq);
	    
	    session.getTransaction().commit();
	    session.clear();
	    session.close();
	}

	@Override
	public List<EmployeeRequisition> getAllEmployeeRequisition() {
		
		return this.employeRequisitionDao.findAll();
	}

	@Override
	public EmployeeRequisition findEmployeeRequisitiondById(String reqCode) {
		
		try {
			Session session = sessionFactory.openSession();
			Query<EmployeeRequisition> query = session.createQuery("from EmployeeRequisition e where e.reqCode = :reqCode", EmployeeRequisition.class);
			query.setParameter("reqCode", reqCode);
			EmployeeRequisition er = query.getSingleResult();
			return er;
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void updateEmployeeRequisition(EmployeeRequisition c) {
		
		Session session = sessionFactory.openSession();
		
		EmployeeRequisition emp = session.find(EmployeeRequisition.class, c.getReqCode());
		emp.getEmployeRequisitionDetail().clear();
		
		emp.getEmployeRequisitionDetail().addAll(c.getEmployeRequisitionDetail());
		
		session.beginTransaction();
		session.merge(c);
		session.getTransaction().commit();
		
		
	}

	@Override
	public void removeEmployeeRequisition(String reqCode) {
		
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Object o = session.get(EmployeeRequisition.class, reqCode);
			EmployeeRequisition e = (EmployeeRequisition) o;
			
			session.delete(e);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	@Override
	public boolean isEmployeeRequisitionExists(String empCode) {
		
		return false;
	}

	@Override
	public List<EmployeeRequisition> findEmployeeReqByStatusN() {
		
		return this.employeRequisitionDao.findEmployeeReqByStatusN();
	}

	@Override
	public List<EmployeeRequisition> findEmployeeReqByStatusY() {
		
		return this.employeRequisitionDao.findEmployeeReqByStatusY();
	}

	@Override
	public void approvedByReqCode(String reqCode) {
		this.employeRequisitionDao.approvedStatusByReqCode(reqCode);
		
		
	}

	@Override
	public void approvedByReqCodeAndStatus(String reqCode, String requisitionApproval) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
	
		try {
			tx = session.beginTransaction();
			EmployeeRequisition em = session.find(EmployeeRequisition.class, reqCode);
			em.setStatus(requisitionApproval);
			session.merge(em);
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<EmployeeRequisition> getAllPendingEmployeeRequisition() {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<EmployeeRequisition> query = session.createQuery("from EmployeeRequisition e where e.status = :status", EmployeeRequisition.class);
			query.setParameter("status", "N");
			tx.commit();
			return query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

}
