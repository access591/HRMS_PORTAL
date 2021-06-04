package com.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.StaffPostingDuties;
import com.hrms.repository.SaffPostingDutiesDao;

@Service
public class SaffPostingDutiesServiceImpl implements StaffPostingDutiesService {
	@Autowired
	SaffPostingDutiesDao saffPostingDutiesDao;
	@Override
	public void addStaffPostingDuties(StaffPostingDuties staffduties) {
		staffduties.setJobCode(saffPostingDutiesDao.getMaxId("SDC"));	
		this.saffPostingDutiesDao.saveOrUpdate(staffduties);
	}

}
