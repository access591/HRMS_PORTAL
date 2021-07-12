package com.hrms.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.EmployeeAcr;
import com.hrms.repository.EmployeeAcrDao;
@Service
public class EmployeeAcrServiceImpl implements EmployeeAcrService {
@Autowired  EmployeeAcrDao  employeeAcrDao;
@Autowired SessionFactory sessionFactory;
@Override
	public List<EmployeeAcr> getAllEmployeeAcr() {
	
		return employeeAcrDao.findAll();
	}
	@Override
	public boolean addEmployeeAcr(EmployeeAcr employeeAcr) {
	Session session = sessionFactory.openSession();
		
		try {
		
			session.beginTransaction();
			session.save(employeeAcr);
			session.getTransaction().commit();
		
		return true;
	
	} catch (HibernateException e) {
		
		e.printStackTrace();
	}
	 finally {
			session.clear();
			session.close();
	}
		
	return false;
	}
	@Override
	public void removeEmployeeAcr(long id) {
	this.employeeAcrDao.delete(id);
		
	}
}
