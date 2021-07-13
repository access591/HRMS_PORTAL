package com.hrms.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.EmployeeTransfer;
import com.hrms.repository.EmployeeTransferDao;

@Service
public class EmployeeTransferServiceImpl implements EmployeeTransferService {
@Autowired EmployeeTransferDao employeeTransferDao;
@Autowired SessionFactory sessionFactory;
@Override
public List<EmployeeTransfer> getAllEmployeeTransfer() {

	try {
		return employeeTransferDao.findAll();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	return null;
}

@Override
public boolean addEmployeeTransfer(EmployeeTransfer employeeTrns) {
	Session session = sessionFactory.openSession();

	try {

		session.beginTransaction();
		session.save(employeeTrns);
		session.getTransaction().commit();

		return true;

	} catch (HibernateException e) {

		e.printStackTrace();
	} finally {
		session.clear();
		session.close();
	}

	return false;

}
}
