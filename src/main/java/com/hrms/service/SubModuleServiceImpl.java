package com.hrms.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Module;
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

	@Override
	public SubModule findSubModuleById(String id) {
		return subModuleDao.findSubModuleById(id);	}

	@Override
	public void update(Map<String, String> map) {
		SubModule subModule = new SubModule();
		subModule.setSubModuleCode(map.get("updateSubModuleCode"));
		subModule.setSubModuleName(map.get("updateSubModuleName"));
		subModule.setModuleCode(map.get("updateModuleCode"));
		subModule.setInsertedBySubModule(map.get("updateInsertedby"));
		subModule.setInsertedDateSubModule(new Date());
		subModule.setUpdateBySubModule(map.get("updateupdatedby"));
		subModule.setUpdatedDateSubModule(new Date());
		subModule.setAcitveSubModule(map.get("updatestatus"));
		subModule.setSeqNoSubModule(Integer.parseInt(map.get("updateSeq")));
		subModuleDao.update(subModule);
		
	}

	@Override
	public void delete(Map<String, String> map) {
		SubModule subModule = new SubModule();
		subModule.setSubModuleCode(map.get("deleteSubModuleCode"));
		subModuleDao.delete(subModule);
		
	}


}
