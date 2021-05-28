package com.hrms.service;

import java.util.List;

import com.hrms.model.AttendanceRegister;
public interface AttendanceRegisterService {
	public void addAttendanceRegister(AttendanceRegister atndReg);
	List<AttendanceRegister>getAllAttendanceRegisters();
	AttendanceRegister findAttendanceRegisterById(String id);
	public void updateAttendanceRegister(AttendanceRegister c);
	public void removeAttendanceRegister(String id);
}
