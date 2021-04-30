package com.hrms.service;

import java.sql.Date;
import java.util.List;


import com.hrms.model.LeaveRequest;

public interface LeaveRequestService {

	List<LeaveRequest> findAllByEmpCode(String empCode);
	
	public void addLeave(LeaveRequest leaveRequest);
	List<LeaveRequest> getAllLeaves();
	//LeaveRequest findLeaveRequestById(String id);
	public void updateLeaveRequest(LeaveRequest d);
	public void removeLeaveRequest(Long id);
	List<LeaveRequest> findByEmpCodeAndApplyDate(String empCode, String applyDate);
	public List<LeaveRequest> findAllByDeptCodeAndStatusN(String deptCode);

	LeaveRequest findLeaveRequestById(long id);
	List<LeaveRequest> getEmployeeByStatusY();
	List<LeaveRequest> findByEmpBetweenDate(String empCode, Date toDate, Date fromDate);
	 LeaveRequest findByToDate(Date date);
}
