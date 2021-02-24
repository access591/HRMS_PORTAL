package com.hrms.service;

import java.util.List;

import com.hrms.model.LeaveDetail;

public interface LeaveDetailService {
	
	public void addLeaveDetail(LeaveDetail leaveDetail);

	List<LeaveDetail> getAllLeaveDetails();

	LeaveDetail findLeaveDetailById(String id);

	public void updateTravel(LeaveDetail c);

	public void removeLeaveDetail(String id);

}
