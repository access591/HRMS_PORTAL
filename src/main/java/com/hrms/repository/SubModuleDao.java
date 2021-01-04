package com.hrms.repository;

import java.util.List;


import com.hrms.model.SubModule;

public interface SubModuleDao
{
	List<SubModule> getAllSubModules();
	public void addSubModule(SubModule subModule);
	SubModule findSubModuleById(String id);
	public void updateSubModule(SubModule subModule);
	public void removeSubModule(String id);
	
	
	
}
