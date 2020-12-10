package com.hrms.service;

import java.util.List;

import com.hrms.model.Program;

public interface ProgramService {
	
	List<Program> getPrograms();

	void addProgram(Program program);

}
