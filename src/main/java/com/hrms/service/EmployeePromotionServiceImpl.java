package com.hrms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.EmployeePromotion;
import com.hrms.repository.EmployeePromotionDao;

@Service
public class EmployeePromotionServiceImpl implements EmployeePromotionService {
@Autowired EmployeePromotionDao  employeePromotionDao;
@Autowired SessionFactory sessionFactory;


	@Override
	public boolean addEmployeePromotion(EmployeePromotion employeePromotion) {
	Session s=sessionFactory.openSession();s.beginTransaction();
	s.save(employeePromotion);s.getTransaction().commit();s.clear();s.close();
	return true;
	}
	@Override
	public List<EmployeePromotion> getAllEmployeePromotion() {
		return employeePromotionDao.findAll();
	}
	@Override
	public void removeEmployeePromotion(long id) {
	this.employeePromotionDao.delete(id);
		
	}
	@Override
	public EmployeePromotion findByIdEmployeePromotion(long id) {
		
		return employeePromotionDao.findById(id);
	}
}
