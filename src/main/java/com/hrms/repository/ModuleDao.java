package com.hrms.repository;

import java.util.List;


import com.hrms.model.Module;

public interface ModuleDao {

	List<Module> getAllModules();

	void addModule(Module module);
	Module findModule(Module module);
	   Module findModuleById(String id);
	   public void updateModule(Module m); 
	   public void removeModule(String id);
	   List<Module>getActiveModules();
	   List<Module> getAllModulesss(String userCode);
	   

}
