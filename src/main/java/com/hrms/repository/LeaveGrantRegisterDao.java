package com.hrms.repository;

import com.hrms.dao.GenericDao;
import com.hrms.model.LeaveGrant;

public interface LeaveGrantRegisterDao  extends GenericDao<LeaveGrant>{

	public LeaveGrant findLeaveGrantByEmp(String empCode);
}
