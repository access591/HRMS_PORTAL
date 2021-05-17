package com.hrms.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.LocalConvyenceDetail;
import com.hrms.model.TourPlanDetails;
import com.hrms.repository.LocalConvyenceDetailDao;

@Service
public class LocalConvyenceDetailServiceImpl implements LocalConvyenceDetailService{
	
	  @Autowired LocalConvyenceDetailDao localConvyenceDetailDao;
	 

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean addTourPlanDetail(LocalConvyenceDetail localConvyenceDetail) {
		
		
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		
		// TODO Auto-generated method stub
		s.save(localConvyenceDetail);
		s.getTransaction().commit();
		s.clear();
		s.close();
		return true;
	}

}
