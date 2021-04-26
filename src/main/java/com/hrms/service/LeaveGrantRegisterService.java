package com.hrms.service;



import java.util.List;


import com.hrms.model.LeaveGrant;

public interface LeaveGrantRegisterService {
    public void addLeaveGrant(LeaveGrant leaveGrant);
	List<LeaveGrant>getAllLeaveGrants();
	LeaveGrant findLeaveGrantById(String id);
	public void updateLeaveGrant(LeaveGrant l);
	public void removeLeaveGrant(String id);
}
