package com.hrms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.State;
@Repository
public class StateDaoImpl extends AbstractGenericDao<State> implements StateDao{
	@Autowired SessionFactory sessionFactory;
	Session session=null;
	Query query =null;
	@Override
	public List<State> findStateByCountry(String country) {
	
		session = this.sessionFactory.getCurrentSession();
		query = session.createQuery("from State e where e.countryCode.countryCode= :countryCode and e.active=:status");
		query.setParameter("countryCode", country);
		query.setParameter("status", "Y");
		List<State> stateList = query.list();
		return stateList;
	}

}
