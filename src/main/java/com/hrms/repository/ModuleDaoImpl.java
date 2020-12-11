package com.hrms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.model.Module;

@Repository
public class ModuleDaoImpl implements ModuleDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Module> getAllModules() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Module.class);
		List<Module> modules = (List<Module>) criteria.setFetchMode("M_MODULE", FetchMode.SELECT).list();
		session.close();
		return modules;
	}

	@Override
	public void addModule(Module module) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(module);
		tx.commit();
		session.close();
	}

	@Override
	public void update(Module module) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Module updatedModule = ( Module ) session.load(Module.class, module.getModuleCode());
		updatedModule.setModuleName(module.getModuleName());
		updatedModule.setSeqNo(module.getSeqNo());
		updatedModule.setActive(module.getActive());
		updatedModule.setInsertedBy(module.getInsertedBy());
		updatedModule.setInsertedDate(module.getInsertedDate());
		session.update(updatedModule);
		tx.commit();
		session.close();
	}

	@Override
	public int delete(Module module) {
		return 0;
	}

	@Override
	public Module findModuleById(String id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Module.class);
		Module modules = (Module) criteria.setFetchMode("M_MODULE", FetchMode.SELECT)
				.add(Restrictions.eq("moduleCode", id)).uniqueResult();
		session.close();
		return modules;
	}

}
