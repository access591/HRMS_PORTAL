package com.hrms.repository;

import java.util.List;

import com.hrms.model.Program;

public interface ProgramDao {

	List<Program> getAllPrograms();
	Program checkProgramExists(Program program);
	void addProgram(Program program);
	 Program findProgramById(String id);
	  public void updateProgram(Program p); 
}
