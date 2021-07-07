package com.hrms.service;

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

			Session s=sessionFactory.openSession();
			s.beginTransaction();
			s.save(employeePromotion);
			s.getTransaction().commit();
			s.clear();
			s.close();
			return true;
	}

}
