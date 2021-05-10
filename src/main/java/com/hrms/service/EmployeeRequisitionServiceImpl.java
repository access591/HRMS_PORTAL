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
	@Autowired SessionFactory sessionfactory;
	//@Autowired
	@Override
	public void addEmployeeRequisition(EmployeeRequisition employeReq) {
		Session session = sessionfactory.openSession();
	    session.beginTransaction();
	    employeReq.setReqCode(employeRequisitionDao.getMaxId("REQ"));
	    session.save(employeReq);
	    //session.saveOrUpdate(employeReq);
	    session.getTransaction().commit();
	    session.clear();
	    session.close();
	}

	@Override
	public List<EmployeeRequisition> getAllEmployeeRequisition() {
		
		return this.employeRequisitionDao.findAll();
	}

	@Override
	public EmployeeRequisition findEmployeeRequisitiondById(String empCode) {
		
		return this.employeRequisitionDao.findById(empCode);
	}

	@Override
	public void updateEmployeeRequisition(EmployeeRequisition c) {
		
		System.out.println("employee req code>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> : "+ c.getReqCode());
		c.setReqCode(c.getReqCode());
		
		  Session session = sessionfactory.openSession(); session.beginTransaction();
		  //employeReq.setReqCode(employeRequisitionDao.getMaxId("REQ"));
		  EmployeeRequisition req = session.find(EmployeeRequisition.class, c.getReqCode());
		  req.setEmployeRequisitionDetail(null);
		  req.setEmployeRequisitionDetail(c.getEmployeRequisitionDetail());
		  session.merge(c); //session.saveOrUpdate(employeReq);
		  session.getTransaction().commit(); 
		  session.clear(); 
		  session.close();
		 
		this.employeRequisitionDao.saveOrUpdate(c);
		
		
	}

	@Override
	public void removeEmployeeRequisition(String reqCode) {
		Session session = sessionfactory.openSession();
		Object o = session.get(EmployeeRequisition.class, reqCode);
		EmployeeRequisition e = (EmployeeRequisition) o;
		Transaction tx = session.beginTransaction();
		session.delete(e);
		tx.commit();
		session.close();
		
		//Query q = session.createQuery("delete EmployeeRequisition e where e.reqCode = :reqCode");
		//q.setParameter("reqCode", reqCode);
		//q.executeUpdate();
	}

	@Override
	public boolean isEmployeeRequisitionExists(String empCode) {
		// TODO Auto-generated method stub
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
		//this.employeRequisitionDao.approvedStatusByReqCode(reqCode);
		Session session = sessionfactory.openSession();
		Object o = session.get(EmployeeRequisition.class, reqCode);
		
		EmployeeRequisition e = (EmployeeRequisition) o;
		//e.setEmployeRequisitionDetail(null);
		//e.setEmployeRequisitionDetail(e.getEmployeRequisitionDetail());
		  
		e.setStatus("Y");
		//Transaction tx = session.beginTransaction();
		//Query query = session.createQuery("UPDATE EmployeeRequisition e set e.status = 'Y' WHERE e.reqCode = :reqCode");
		//query.setParameter("reqCode", reqCode);
		//tx.commit();
		session.update(e);
		//session.close();
		
	}

}
