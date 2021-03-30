package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.LeaveRequest;
import com.hrms.repository.LeaveRequestDao;


@Service
public class LeaveRequestServiceImpl implements LeaveRequestService{

	@Autowired LeaveRequestDao leaveRequestDao;
	@Override
	public List<LeaveRequest> findAllByName(String empCode) {
		
		return this.leaveRequestDao.findAllByName(empCode);
		
	}
	@Override
	public void addLeave(LeaveRequest leaveRequest) {
		this.leaveRequestDao.saveOrUpdate(leaveRequest);
		
	}
	@Override
	public List<LeaveRequest> getAllLeaves() {
		
		return this.leaveRequestDao.findAll();
	}
	@Override
	public LeaveRequest findLeaveRequestById(String id) {
		
		return this.leaveRequestDao.findById(id);
	}
	@Override
	public void updateLeaveRequest(LeaveRequest d) {
		this.leaveRequestDao.saveOrUpdate(d);
		
	}
	@Override
	public void removeLeaveRequest(String id) {
		this.leaveRequestDao.delete(id);
		
	}

}