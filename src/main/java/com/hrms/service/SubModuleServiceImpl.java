package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.SubModule;
import com.hrms.repository.SubModuleDao;

@Service
public class SubModuleServiceImpl  implements SubModuleService{

	
	@Autowired
	private SubModuleDao subModuleDao;
	
	@Override
	public List<SubModule> getAllSubModules() {
	
		List<SubModule> listSubModule = subModuleDao.getAllSubModules();
		return listSubModule;
	
	}

	@Override
	public void addSubModule(SubModule subModule) 
	{
	
		this.subModuleDao.addSubModule(subModule);
		
	}

	@Override
	public void updateSubModule(SubModule subModule)
	{
	this.subModuleDao.updateSubModule(subModule);
		
	}

}
