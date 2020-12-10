package com.hrms.repository;

import java.util.List;

import com.hrms.model.Program;

public interface ProgramDao {

	List<Program> getPrograms();

	void addProgram(Program program);
}
