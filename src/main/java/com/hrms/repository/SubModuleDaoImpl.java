package com.hrms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.model.SubModule;
import com.hrms.model.UserEntity;

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
		System.out.println("call add method ====================");
		session.persist(subModule);
		tx.commit();

	}

	@Override
	public void updateSubModule(SubModule subModule) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(SubModule.class);
		System.out.println("call add method ====================");
		session.update(subModule);
		tx.commit();

	}

}
