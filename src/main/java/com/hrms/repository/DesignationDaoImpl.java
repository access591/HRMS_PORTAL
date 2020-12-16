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

import com.hrms.model.Designation;

@Repository
public class DesignationDaoImpl implements DesignationDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addDesignation(Designation designation)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(designation);
		tx.commit();
		session.close();
		
	}

	@Override
	public List<Designation> getAllDesignations() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Designation.class);
		 List<Designation> listDesignation=(List<Designation>)criteria.setFetchMode("M_DESIGNATION", FetchMode.SELECT).list();
		 
		return listDesignation;
	}

	@Override
	public Designation findDesignationById(String id) {
		  Session session = sessionFactory.openSession();
		   Criteria criteria = session.createCriteria(Designation.class);
		   Designation designationEdit =(Designation) criteria.setFetchMode("M_DESIGNATION", FetchMode.SELECT).add(Restrictions.eq("Desg_code",id)).uniqueResult(); 

		   return designationEdit;
		 
	
	}
	
	

}
