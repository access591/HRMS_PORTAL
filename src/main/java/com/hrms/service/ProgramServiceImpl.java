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
		return programDao.getAllPrograms();
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

	@Override
	public Program findProgramById(String id) {
		
		return programDao.findProgramById(id);
	}

	@Override
	public void updateProgram(Program p) {
		Module m=new Module();
		SubModule S=new SubModule();
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
		this.programDao.updateProgram(p);
		
	}
	

}
