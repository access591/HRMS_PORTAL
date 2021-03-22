package com.hrms.repository;

import com.hrms.model.LeaveGrant;

public interface LeaveGrantRegisterDao {
	LeaveGrant findYearAndType(String year,String leaveType);
}
