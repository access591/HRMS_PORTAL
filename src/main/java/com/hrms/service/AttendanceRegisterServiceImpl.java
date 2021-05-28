package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.AttendanceRegister;
import com.hrms.repository.AttendanceRegisterDao;

@Service
public class AttendanceRegisterServiceImpl implements AttendanceRegisterService {
@Autowired 
AttendanceRegisterDao attendanceRegisterDao;

	@Override
	public void addAttendanceRegister(AttendanceRegister atndReg) {
		
	}

	@Override
	public List<AttendanceRegister> getAllAttendanceRegisters() {

		return null;
	}

	@Override
	public AttendanceRegister findAttendanceRegisterById(String id) {
	
		return null;
	}

	@Override
	public void updateAttendanceRegister(AttendanceRegister c) {
	
		
	}

	@Override
	public void removeAttendanceRegister(String id) {
	
		
	}

}
