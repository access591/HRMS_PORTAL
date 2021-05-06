package com.hrms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.EmployeeRequisition;
import com.hrms.repository.EmployeeRequisitionDao;

@Service
public class EmployeeRequisitionServiceImpl implements EmployeeRequisitionService{

	@Autowired EmployeeRequisitionDao employeRequisitionDao;
	@Autowired SessionFactory sessionfactory;
	@Override
	public void addEmployeeRequisition(EmployeeRequisition employeReq) {
		Session session = sessionfactory.openSession();
	    session.beginTransaction();
	    employeReq.setReqCode(employeRequisitionDao.getMaxId("REQ"));
	    session.save(employeReq);
	    //session.saveOrUpdate(employeReq);
	    session.getTransaction().commit();
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
		this.employeRequisitionDao.saveOrUpdate(c);
		
	}

	@Override
	public void removeEmployeeRequisition(String id) {
		this.employeRequisitionDao.delete(id);
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

}
