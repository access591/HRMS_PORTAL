package com.hrms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
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
		criteria.addOrder(Order.asc("seqNo"));
		List<Module> modules = (List<Module>) criteria.setFetchMode("M_MODULE", FetchMode.SELECT).list();
		
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

	

}
