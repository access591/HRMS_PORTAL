package com.hrms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.model.Module;
import com.hrms.model.Program;
import com.hrms.model.SubModule;

@Repository
public class ModuleDaoImpl implements ModuleDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Module> getAllModules() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Module.class);
		List<Module> modules = (List<Module>) criteria.setFetchMode("M_MODULE", FetchMode.SELECT).list();
//		for (Module module : modules) {
//			List<SubModule> subModules = module.getSubModules();
//			for (SubModule submodule : subModules) {
//				List<Program> subModule = subModules.get
//			}
//		}
		
		return modules;
	}

}
