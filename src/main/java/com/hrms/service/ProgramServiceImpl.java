package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Module;
import com.hrms.model.Program;
import com.hrms.model.SubModule;
import com.hrms.repository.ProgramDao;

@Service
public class ProgramServiceImpl implements ProgramService {

	@Autowired
	ProgramDao programDao;

	@Override
	public List<Program> getAllPrograms() {
		return programDao.findAll();
	}

	@Override
	public void addProgram(Program program) {
		program.setProgramCode(programDao.getMaxId("PRG"));
		programDao.saveOrUpdate(program);
	}

	@Override
	public boolean checkProgramExists(Program program) {

		Program e = programDao.existOrNot(program);
		if (e != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Program findProgramById(String id) {

		return programDao.findById(id);
	}

	@Override
	public void updateProgram(Program p) {
		
		this.programDao.saveOrUpdate(p);

	}

	@Override
	public void removeProgram(String id) {
		this.programDao.delete(id);

	}

	@Override
	public List<Program> getActivePrograms() {
		return programDao.findAll();
	}

}
