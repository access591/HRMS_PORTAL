package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Program;

import com.hrms.repository.ProgramDao;

@Service
public class ProgramServiceImpl implements ProgramService {

	@Autowired
	ProgramDao programDao;

	@Override
	public List<Program> getAllPrograms() {
		return programDao.getPrograms();
	}

	@Override
	public void addProgram(Program program) {
		programDao.addProgram(program);
	}

	@Override
	public boolean checkProgramExists(Program program) {
		
		Program e = programDao.checkProgramExists(program);
			if (e != null) {
				return true;
			} else {
				return false;
			}
	}
	

}
