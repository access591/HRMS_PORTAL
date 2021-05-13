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
		
		Session session = sessionfactory.openSession();
		//Transaction tx = session.beginTransaction();
		EmployeeRequisition emp = session.find(EmployeeRequisition.class, c.getReqCode());
		emp.getEmployeRequisitionDetail().clear();;
		emp.setEmployeRequisitionDetail(c.getEmployeRequisitionDetail());
		emp.getEmployeRequisitionDetail().addAll(c.getEmployeRequisitionDetail());
		session.update(emp);
		//tx.commit();
		session.close();
		//this.employeRequisitionDao.saveOrUpdate(c);
		
		
	}

	@Override
	public void removeEmployeeRequisition(String reqCode) {
		Session session = sessionfactory.openSession();
		Transaction tx = session.beginTransaction();
		Object o = session.get(EmployeeRequisition.class, reqCode);
		EmployeeRequisition e = (EmployeeRequisition) o;
		
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
		this.employeRequisitionDao.approvedStatusByReqCode(reqCode);
		
		
	}

}
