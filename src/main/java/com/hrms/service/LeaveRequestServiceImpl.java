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
	public List<LeaveRequest> findAllByEmpCode(String empCode) {
		
		return this.leaveRequestDao.findAllByEmpCode(empCode);
		
	}
	@Override
	public void addLeave(LeaveRequest leaveRequest) {
		this.leaveRequestDao.saveOrUpdate(leaveRequest);
		
	}
	@Override
	public List<LeaveRequest> getAllLeaves() {
		//System.out.println("DAO MODEL : "+this.leaveRequestDao.findAll().get(1).getEmpName());
		return this.leaveRequestDao.findAll();
	}
	
	@Override
	public LeaveRequest findLeaveRequestById(long id) {
		
		return this.leaveRequestDao.findById(id);
	}
	@Override
	public void updateLeaveRequest(LeaveRequest d) {
		this.leaveRequestDao.saveOrUpdate(d);
		
	}
	@Override
	public void removeLeaveRequest(Long id) {
		this.leaveRequestDao.delete(id);
		
	}
	@Override
	public List<LeaveRequest> findByEmpCodeAndApplyDate(String empCode, String applyDate) {
		
		return this.leaveRequestDao.findByEmpCodeAndApplyDate(empCode, applyDate);
	}
	@Override
	public List<LeaveRequest> findAllByDeptCodeAndStatus(String deptCode) {
		return this.leaveRequestDao.findAllByDeptCodeAndStatus(deptCode);
	}
	@Override
	public List<LeaveRequest> getEmployeeByStatusY() {
		
		return this.leaveRequestDao.getEmployeeByStatusY();
	}

}
