package com.hrms.service;

import java.util.List;


import com.hrms.model.SubModule;

public interface SubModuleService {
	List<SubModule> getAllSubModules();

	boolean checkSubModuleExists(SubModule subModule);

	public void addSubModule(SubModule subModule);

	SubModule findSubModuleById(String id);

	public void updateSubModule(SubModule subModule);

	public void removeSubModule(String id);
    
} 