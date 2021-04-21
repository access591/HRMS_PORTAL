package com.hrms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Login;
import com.hrms.model.MenuModule;
import com.hrms.model.Module;
import com.hrms.model.Program;
import com.hrms.model.SubModule;
import com.hrms.model.SubModuleProgram;
import com.hrms.repository.ModuleDao;

@Service
public class ModuleServiceImpl implements ModuleService {
	Login login=new Login();
	@Autowired
	private ModuleDao moduleDao;

	@Override

	public List<MenuModule> getAllModules() {

		List<MenuModule> menuModulelist = processModules(moduleDao.findAll());
		return menuModulelist;
	}

	@Override
	public List<MenuModule> getAllModulesList(String userCode) {

		login.setUserCode(userCode);

		List<MenuModule> menuModulelist = processModules(moduleDao.getAllModulesList(userCode));
		return menuModulelist;
	}

	private List<MenuModule> processModules(List<Module> modules) {
		List<MenuModule> menuModuleList = new ArrayList<MenuModule>();

		for (Module module : modules) {

			MenuModule menuModule = new MenuModule();
			menuModule.setModuleCode(module.getModuleCode());
			menuModule.setModuleName(module.getModuleName());
			menuModule.setSubModuleProgram(getSubModuleProgramsList(module));
			menuModule.setPrograms(getModuleProgramMap(module));
			menuModuleList.add(menuModule);
		}
		return menuModuleList;
	}

	private List<SubModuleProgram> getSubModuleProgramsList(Module module) {
		List<SubModuleProgram> liSubModulePrograms = new ArrayList<SubModuleProgram>();
		String ucode = login.getUserCode();
		String modulecCode = module.getModuleCode();
		System.out.println("Sub Module User Code " + ucode);

		List<SubModule> subMOdules = moduleDao.getAllSubModule(modulecCode, ucode);
		for (SubModule submodule : subMOdules) {
			SubModuleProgram subModuleProgram = new SubModuleProgram();
			subModuleProgram.setModuleCode(module.getModuleCode());
			subModuleProgram.setModuleName(module.getModuleName());
			subModuleProgram.setSubModuleCode(submodule.getSubModuleCode());
			subModuleProgram.setSubModuleName(submodule.getSubModuleName());
			subModuleProgram.setSubPrograms(getSubModuleProgramMap(submodule, module));
			liSubModulePrograms.add(subModuleProgram);
		}
		return liSubModulePrograms;

	}

	private Map<String, String> getModuleProgramMap(Module module) {
		List<Program> programs = module.getModulePrograms();
		Map<String, String> programMap = new HashMap<String, String>();
		for (Program program : programs) {
			String name = program.getProgramName();
			String href = program.getProgramHrefName();
			programMap.put(name, href);
		}
		return programMap;
	}

	private Map<String, String> getSubModuleProgramMap(SubModule subModule, Module module) {
		String ucode = login.getUserCode();
		String moduleCode = module.getModuleCode();
		String smCode = subModule.getSubModuleCode();
		System.out.println("Program module  User Code Test" + ucode);
		List<Program> programs = moduleDao.getAllProgramList(moduleCode, smCode, ucode);
		Map<String, String> programMap = new HashMap<String, String>();
		for (Program program : programs) {
			String name = program.getProgramName();
			String href = program.getProgramHrefName();
			programMap.put(name, href);
		}
		return programMap;
	}

	@Override
	public void addModule(Module module) {
		module.setInsertedDate(new Date());
		module.setModuleCode(moduleDao.getMaxId("MOD"));
		this.moduleDao.saveOrUpdate(module);

	}

	@Override
	public List<Module> getModules() {
		return moduleDao.findAll();
	}

	@Override
	public boolean checkModuleExists(Module module) {

		Module e = moduleDao.checkModuleExists(module);
		if (e != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Module findModuleById(String id) {
		return moduleDao.findById(id);
	}

	@Override
	public void updateModule(Module m) {
		m.setModuleName(m.getModuleName());
		m.setActive(m.getActive());
		m.setActive(m.getActive());
		m.setUpdatedDate(m.getUpdatedDate());
		this.moduleDao.saveOrUpdate(m);

	}

	@Override
	public void removeModule(String id) {
		this.moduleDao.delete(id);
	}

	@Override
	public List<Module> getActiveModules() {
		return moduleDao.findAll();
	}

	@Override
	public boolean checkModuleSeqExists(Module module) {
	
		Module e = moduleDao.checkModuleSeqExists(module);
		if (e != null) {
			return true;
		} else {
			return false;
		}
	}

}
