package com.hrms.service;

import java.util.List;

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

	@Override
	public void removestaffDuties(String id) {
		this.saffPostingDutiesDao.delete(id);

	}

	@Override
	public List<StaffPostingDuties> getAllStaffPostingDuties() {
		List<StaffPostingDuties> staffPostingDuties = saffPostingDutiesDao.findAll();
		return staffPostingDuties;
	}

	@Override
	public StaffPostingDuties StaffPostingDutieById(String id) {
		return saffPostingDutiesDao.findById(id);
	}

	@Override
	public void UpdateStaffPostingDuties(StaffPostingDuties staffduties) {
		this.saffPostingDutiesDao.saveOrUpdate(staffduties);
		
	}

}
