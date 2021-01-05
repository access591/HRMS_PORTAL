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
import com.hrms.model.SubModule;


@Repository
public class SubModuleDaoImpl implements SubModuleDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<SubModule> getAllSubModules() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(SubModule.class);
		List<SubModule> listSubModule = (List<SubModule>) criteria.setFetchMode("M_SUB_MODULE", FetchMode.SELECT).list();
		return listSubModule;
		
		
	}

	@Override
	public void addSubModule(SubModule subModule) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(SubModule.class);
		
		session.persist(subModule);
		tx.commit();

	}
	@Override
	public SubModule findSubModuleById(String id) {
	
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(SubModule.class);
		SubModule subModuleEdit = (SubModule) criteria.setFetchMode("M_SUB_MODULE", FetchMode.SELECT)
				.add(Restrictions.eq("subModuleCode", id)).uniqueResult();

		return subModuleEdit;
	}
	

	@Override
	public void updateSubModule(SubModule subModule) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(subModule);
		tx.commit();
		session.close();		
		
	}

	@Override
	public void removeSubModule(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		SubModule s = (SubModule) session.load(SubModule.class, new String(id));
		System.out.println("value of G " + s);

		session.delete(s);
		tx.commit();
		session.close();	
		
	}

	@Override
	public SubModule checkSubModuleExists(SubModule subModule) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(SubModule.class);
		SubModule smcode = (SubModule) criteria.setFetchMode("M_SUB_MODULE",FetchMode.SELECT)
				.add(Restrictions.eq("subModuleCode", subModule.getSubModuleCode())).uniqueResult();
		return smcode;
	}

	@Override
	public List<SubModule> getActiveSubModules() {
		
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(SubModule.class);
		List<SubModule> subModulesList = (List<SubModule>) criteria.setFetchMode("M_SUB_MODULE", FetchMode.SELECT).list();
		
		return subModulesList;
	}



}
