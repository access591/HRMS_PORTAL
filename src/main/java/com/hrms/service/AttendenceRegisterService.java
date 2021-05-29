package com.hrms.service;

import java.sql.Date;
import java.util.List;

import com.hrms.model.AttendenceRegister;

public interface AttendenceRegisterService {
	
	public List<AttendenceRegister> findAttendenceByEmpCodeBetweenDate(String empCode,Date FromDate,Date toDate);

	public List<AttendenceRegister> findAttendenceByDeptBetweenDate(String deptCode,Date FromDate,Date toDate);
	
	public List<AttendenceRegister> findAllAttendenceBetweenDate(Date FromDate,Date toDate);

	public AttendenceRegister findAttendenceRegisterByEmpCode(String empCode);
	
	public List<AttendenceRegister> findAttendenceByEmpStatusAbsent(String empCode);
}
