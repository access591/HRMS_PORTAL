package com.hrms.service;

import java.util.List;

import com.hrms.model.SubModule;

public interface SubModuleService {
	List<SubModule> getAllSubModules();
	public void addSubModule(SubModule subModule);
	public void updateSubModule(SubModule subModule);

}
