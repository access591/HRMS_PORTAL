package com.hrms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.MenuModule;
import com.hrms.model.Module;
import com.hrms.model.Program;
import com.hrms.model.SubModule1;
import com.hrms.model.SubModuleProgram;
import com.hrms.repository.ModuleDao;

@Service
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleDao moduleDao;

	@Override
	public List<MenuModule> getAllModules() {

		List<MenuModule> menuModulelist = processModules(moduleDao.getAllModules());
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

//		for (SubModule submodule : module.getSubModules()) {
//			SubModuleProgram subModuleProgram = new SubModuleProgram();
//			subModuleProgram.setModuleCode(module.getModuleCode());
//			subModuleProgram.setModuleName(module.getModuleName());
//			subModuleProgram.setSubModuleCode(submodule.getSubModuleCode());
//			subModuleProgram.setSubModuleName(submodule.getSubModuleName());
//			subModuleProgram.setSubPrograms(getSubModuleProgramMap(submodule));
//			liSubModulePrograms.add(subModuleProgram);
//		}
		return liSubModulePrograms;

	}

	private Map<String, String> getModuleProgramMap(Module module) {
	//	List<Program> programs = module.getModulePrograms();
		Map<String, String> programMap = new HashMap<String, String>();
//		for (Program program : programs) {
//			String name = program.getProgramName();
//			String href = program.getProgramHrefName();
//			programMap.put(name, href);
//		}
		return programMap;
	}

	private Map<String, String> getSubModuleProgramMap(SubModule1 subModule) {
		List<Program> programs = subModule.getSubModulePrograms();
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
		moduleDao.addModule(module);
	}

	@Override
	public void update(Module module) {
		moduleDao.update(module);
	}

	@Override
	public int delete(Module module) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Module> getModules() {
		return moduleDao.getAllModules();
	}

	@Override
	public Module findModuleById(String id) {
		return moduleDao.findModuleById(id);
	}

}
