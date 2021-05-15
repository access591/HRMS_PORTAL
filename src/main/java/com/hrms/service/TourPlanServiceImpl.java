package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.TourPlan;
import com.hrms.repository.TourPlanDao;

@Service
public class TourPlanServiceImpl implements TourPlanService{

	@Autowired TourPlanDao tourPlanDao;

	@Override
	public void addTourPlan(TourPlan tourPlan) {
		
		tourPlan.setTourPlanId(tourPlanDao.getMaxId("TPL"));
		this.tourPlanDao.saveOrUpdate(tourPlan);
		
	}

	@Override
	public List<TourPlan> getAllTourPlan() {
		
		return this.tourPlanDao.findAll();
	}

	
	@Override
	public void updateTourPlan(TourPlan c) {
		this.tourPlanDao.saveOrUpdate(c);
		
	}

	@Override
	public void removeTourPlan(Long id) {
		this.tourPlanDao.delete(id);
		
	}

	@Override
	public TourPlan findByIdTourPlan(String id) {
		return this.tourPlanDao.findById(id);
	}

}
