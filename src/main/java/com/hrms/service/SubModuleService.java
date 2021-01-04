package com.hrms.service;

import java.util.List;
import java.util.Map;

import com.hrms.model.SubModule;

public interface SubModuleService {
	List<SubModule> getAllSubModules();
	public void addSubModule(SubModule subModule);
	 SubModule findSubModuleById(String id);
	 public void updateSubModule(SubModule subModule);
	 public void removeSubModule(String id);
    
} 