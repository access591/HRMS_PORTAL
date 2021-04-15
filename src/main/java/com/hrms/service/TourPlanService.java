package com.hrms.service;

import java.util.List;

import com.hrms.model.TourPlan;


public interface TourPlanService {

	public void addTourPlan(TourPlan tourPlan);
	List<TourPlan>getAllTourPlan();
	TourPlan findTourPlanById(Long id);
	public void updateTourPlan(TourPlan c);
	public void removeTourPlan(Long id);
}
