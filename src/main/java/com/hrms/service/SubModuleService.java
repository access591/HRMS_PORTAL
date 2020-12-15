package com.hrms.service;

import java.util.List;
import java.util.Map;

import com.hrms.model.SubModule;

public interface SubModuleService {
	List<SubModule> getAllSubModules();
	public void addSubModule(SubModule subModule);
	public void updateSubModule(SubModule subModule);
	SubModule findSubModuleById(String id);
	void update(Map<String, String> map);
   void  delete(Map<String, String> map);
} 