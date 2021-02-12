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

		List<SubModule> listSubModule = subModuleDao.findAll();
		return listSubModule;

	}

	@Override
	public void addSubModule(SubModule subModule) {
		subModule.setSubModuleCode(subModuleDao.getMAX_Id("SUB"));
		this.subModuleDao.saveOrUpdate(subModule);

	}

	@Override
	public SubModule findSubModuleById(String id) {
		return subModuleDao.findById(id);
	}

	@Override
	public void updateSubModule(SubModule subModule) {
		subModule.setSubModuleName(subModule.getSubModuleName());
		subModule.setModuleCode(subModule.getModuleCode());
		subModule.setSeqNoSubModule(subModule.getSeqNoSubModule());
		subModule.setAcitveSubModule(subModule.getAcitveSubModule());
		this.subModuleDao.saveOrUpdate(subModule);

	}

	@Override
	public void removeSubModule(String id) {
		this.subModuleDao.delete(id);
	}

	@Override
	public boolean checkSubModuleExists(SubModule subModule) {
		SubModule e = subModuleDao.existOrNot(subModule);
		if (e != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<SubModule> getActiveSubModules() {
		return subModuleDao.findAll();
	}

}
