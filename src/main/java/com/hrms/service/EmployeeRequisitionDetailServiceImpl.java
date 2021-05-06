package com.hrms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.EmployeeRequisitionDetail;
import com.hrms.repository.EmployeeRequisitionDetailDao;

@Service
public class EmployeeRequisitionDetailServiceImpl implements EmployeeRequisitionDetailService{

	@Autowired EmployeeRequisitionDetailDao  employeeRequisitionDetailDao;
	@Autowired SessionFactory sessionFactory;
	@Override
	public void addEmployeeRequisitionDetail(EmployeeRequisitionDetail employeReq) {
		Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    //employeReq.setReqCode("001");
	    session.save(employeReq);
	    //session.saveOrUpdate(employeReq);
	    session.getTransaction().commit();
	}

	@Override
	public List<EmployeeRequisitionDetail> getAllEmployeeRequisitionDetail() {
		
		return this.employeeRequisitionDetailDao.findAll();
	}

	@Override
	public EmployeeRequisitionDetail findEmployeeRequisitiondById(String empCode) {
		
		return this.employeeRequisitionDetailDao.findById(empCode);
	}

	@Override
	public void updateEmployeeRequisition(EmployeeRequisitionDetail c) {
		this.employeeRequisitionDetailDao.saveOrUpdate(c);
	}

	@Override
	public void removeEmployeeRequisitionDetail(String id) {
		this.employeeRequisitionDetailDao.delete(id);
		
	}

	@Override
	public boolean isEmployeeRequisitionDetailExists(String empCode) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
