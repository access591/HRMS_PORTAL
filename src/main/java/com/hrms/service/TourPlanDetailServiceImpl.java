package com.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.repository.TourPlanDetailDao;
import com.hrms.model.TourPlanDetails;

@Service
public class TourPlanDetailServiceImpl implements TourPlanDetailService{
	@Autowired
	TourPlanDetailDao TourPlanDetailDao;
	@Override
	public boolean addTourPlanDetail(TourPlanDetails tourPlanDetail) {
		// TODO Auto-generated method stub
		this.TourPlanDetailDao.saveOrUpdate(tourPlanDetail);
		return false;
	}

}
