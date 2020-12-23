package com.hrms.repository;

import java.util.List;

import com.hrms.model.Leave;


public interface LeaveDao
{
	List<Leave>getAllLeaves();
	void addLeave(Leave leave);
	Leave findLeaveById(String id);
	public void updateLeave(Leave d);
	public void removeLeave(String id);
}
