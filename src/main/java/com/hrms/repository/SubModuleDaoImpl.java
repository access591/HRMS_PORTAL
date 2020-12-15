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

	@Override
	public SubModule findSubModuleById(String id) {
		Session session = sessionFactory.openSession();
		System.out.println("heloo====================================="+id);
		Criteria criteria = session.createCriteria(Module.class);
		SubModule subModuleEdit = (SubModule) criteria.setFetchMode("M_SUB_MODULE", FetchMode.SELECT)
				.add(Restrictions.eq("SUB_MODULE_CODE", id)).uniqueResult();
		return subModuleEdit;
	}

	@Override
	public void update(SubModule subModule) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		SubModule updatedSubModule = ( SubModule ) session.load(SubModule.class, subModule.getSubModuleCode());
		updatedSubModule.setSubModuleName(subModule.getSubModuleName());
		updatedSubModule.setModuleCode(subModule.getModuleCode());
		updatedSubModule.setUpdateBySubModule(subModule.getUpdateBySubModule());
		updatedSubModule.setUpdatedDateSubModule(subModule.getUpdatedDateSubModule());
		updatedSubModule.setAcitveSubModule(subModule.getAcitveSubModule());
		updatedSubModule.setSeqNoSubModule(subModule.getSeqNoSubModule());
		session.update(updatedSubModule);
		tx.commit();
		session.close();
	}

	@Override
	public void delete(SubModule subModule) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		SubModule deleteSubModule = ( SubModule ) session.load(SubModule.class, subModule.getSubModuleCode());
		session.delete(deleteSubModule);
		tx.commit();
		session.close();
	}

}
