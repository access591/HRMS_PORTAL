package com.hrms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Program;



@Repository
public class ProgramDaoImpl extends AbstractGenericDao<Program> implements ProgramDao {
	@Autowired SessionFactory sessionFactory;
	Session session=null;
	Query query =null;
	
	@Override
	public List<Program> findByProgramCodeSubModule(String id) {
	

		session = this.sessionFactory.getCurrentSession();
		query = session.createQuery("from Program s where s.subModuleCode.subModuleCode= :subModCode and s.activeYn=:status");
		query.setParameter("subModCode", id);
		query.setParameter("status", "Y");
		List<Program> programList = query.list();
	
		return programList;
	}


	



}
