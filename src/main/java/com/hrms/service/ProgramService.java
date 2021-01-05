package com.hrms.service;

import java.util.List;

import com.hrms.model.Program;


public interface ProgramService {
	
	List<Program> getAllPrograms();
	void addProgram(Program program);
	boolean checkProgramExists(Program program);

}
