package com.hrms.repository;

import java.util.List;

import com.hrms.model.Module;

public interface ModuleDao {

	List<Module> getAllModules();

	void addModule(Module module);

	void update(Module module);

	int delete(Module module);

	Module findModuleById(String id);

}
