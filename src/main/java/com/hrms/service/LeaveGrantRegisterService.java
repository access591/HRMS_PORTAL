package com.hrms.service;



import java.util.List;


import com.hrms.model.LeaveGrant;

public interface LeaveGrantRegisterService {
	LeaveGrant findLeaveGrantById(String id);
	List<LeaveGrant> getAllLeaveGarnt();
}
