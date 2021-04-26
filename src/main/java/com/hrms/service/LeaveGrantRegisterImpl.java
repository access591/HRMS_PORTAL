package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Award;
import com.hrms.model.LeaveGrant;
import com.hrms.repository.LeaveGrantRegisterDao;
@Service
public class LeaveGrantRegisterImpl  implements LeaveGrantRegisterService{
@Autowired
LeaveGrantRegisterDao leaveGrantRegisterDao;

@Override
public void addLeaveGrant(LeaveGrant leaveGrant) {
	this.leaveGrantRegisterDao.saveOrUpdate(leaveGrant);
	
}

@Override
public List<LeaveGrant> getAllLeaveGrants() {
	List<LeaveGrant> listLeaveGrant = leaveGrantRegisterDao.findAll();
	return listLeaveGrant;
}

@Override
public LeaveGrant findLeaveGrantById(String id) {
	return leaveGrantRegisterDao.findById(id);
}

@Override
public void updateLeaveGrant(LeaveGrant l) {
	this.leaveGrantRegisterDao.saveOrUpdate(l);	
	
}

@Override
public void removeLeaveGrant(String id) {
	this.leaveGrantRegisterDao.delete(id);
	
}


@Override
public LeaveGrant findLeaveGrantByEmpCode(String empCode) {
	// TODO Auto-generated method stub
	return this.leaveGrantRegisterDao.findLeaveGrantByEmp(empCode);
}



	

}
