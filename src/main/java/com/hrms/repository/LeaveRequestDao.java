package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.Activities;
import com.hrms.model.LeaveRequest;

public interface LeaveRequestDao  extends GenericDao<LeaveRequest>{
	
	List<LeaveRequest> findAllByName(String empCode);

}