package com.hrms.repository;

import java.util.List;


import com.hrms.model.Module;
import com.hrms.model.Program;
import com.hrms.model.SubModule;

public interface ModuleDao {

	List<Module> getAllModules();

	void addModule(Module module);
	Module findModule(Module module);
	   Module findModuleById(String id);
	   public void updateModule(Module m); 
	   public void removeModule(String id);
	   List<Module>getActiveModules();
	   List<Module> getAllModulesList(String userCode);
	   List<SubModule> getAllSubModule(String modulecCode);
	   List<Program> GetAllProgramList(String moduleCode,String smCode);
	   

}
