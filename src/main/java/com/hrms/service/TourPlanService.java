package com.hrms.service;

import java.util.List;

import com.hrms.model.TourPlan;


public interface TourPlanService {

	public void addTourPlan(TourPlan tourPlan);
	List<TourPlan>getAllTourPlan();
	public void updateTourPlan(TourPlan c);
	public void removeTourPlan(Long id);
	public TourPlan findByIdTourPlan(String id);
}
