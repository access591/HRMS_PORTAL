package com.hrms.service;

import java.util.List;


import com.hrms.model.LeaveRequest;

public interface LeaveRequestService {

	List<LeaveRequest> findAllByEmpCode(String empCode);
	
	public void addLeave(LeaveRequest leaveRequest);
	List<LeaveRequest> getAllLeaves();
	LeaveRequest findLeaveRequestById(String id);
	public void updateLeaveRequest(LeaveRequest d);
	public void removeLeaveRequest(String id);
	List<LeaveRequest> findByEmpCodeAndApplyDate(String empCode, String applyDate);
}
