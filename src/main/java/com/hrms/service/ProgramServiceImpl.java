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
		program.setProgramCode(programDao.getMAX_Id("PRG"));
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
		Module m = new Module();
		SubModule S = new SubModule();
		m.setModuleCode(p.getDmoduleCode());
		S.setSubModuleCode(p.getDsubMouduleCode());
		p.setpModuleCode(m);
		p.setSubModuleCode(S);
		p.setProgramName(p.getProgramName());
		p.setActiveYn(p.getActiveYn());
		p.setProgramHrefName(p.getProgramHrefName());
		p.setProgramType(p.getProgramType());
		p.setSeqProgram(p.getSeqProgram());
		p.setInsertedDate(p.getUpdatedDate());
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
