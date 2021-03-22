package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.LeaveGrant;
import com.hrms.repository.LeaveGrantRegisterDao;
@Service
public class LeaveGrantRegisterImpl  implements LeaveGrantRegisterService{
@Autowired
LeaveGrantRegisterDao leaveGrantRegisterDao;

@Override
public LeaveGrant findLeaveGrantById(String id) {
	
	return null;
}

@Override
public List<LeaveGrant> getAllLeaveGarnt() {
	
	return null;
}
	

}
