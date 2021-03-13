package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.City;
import com.hrms.model.LeaveDetail;
import com.hrms.repository.LeaveDetailDao;
@Service
public class LeaveDetailServiceImpl  implements LeaveDetailService{
	@Autowired
	LeaveDetailDao leaveDetailDao;
	@Override
	public void addLeaveDetail(LeaveDetail leaveDetail) {
		leaveDetail.setLvCode(leaveDetailDao.getMaxId("LVC"));
		this.leaveDetailDao.saveOrUpdate(leaveDetail);
		
	}

	@Override
	public List<LeaveDetail> getAllLeaveDetails() {
	
		List<LeaveDetail> listLeaveDetail = leaveDetailDao.findAll();
		return listLeaveDetail;
	}

	@Override
	public LeaveDetail findLeaveDetailById(String id) {
	
		return this.leaveDetailDao.findById(id);
	}

	@Override
	public void updateLeaveDetail(LeaveDetail leaveDetail) {
	this.leaveDetailDao.saveOrUpdate(leaveDetail);
		
	}

	@Override
	public void removeLeaveDetail(String id) {
	this.leaveDetailDao.delete(id);
		
	}

}
