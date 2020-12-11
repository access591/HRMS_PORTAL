package com.hrms.service;

import java.util.List;
import java.util.Map;

import com.hrms.model.MenuModule;
import com.hrms.model.Module;

public interface ModuleService {
	List<MenuModule> getAllModules();
	
	List<Module> getModules();

	void addModule(Module module);

	void update(Map<String, String> map);

	int delete(Module module);
	
	Module findModuleById(String id);
}
