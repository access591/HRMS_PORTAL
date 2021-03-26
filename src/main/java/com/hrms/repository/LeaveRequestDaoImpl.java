package com.hrms.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.LeaveRequest;
import com.hrms.model.Module;


@Repository
public class LeaveRequestDaoImpl extends AbstractGenericDao<LeaveRequest> implements LeaveRequestDao{

	@Autowired private SessionFactory sessionFactory;
	
	
	@Override
	public List<LeaveRequest> findAllByName(String empCode) {
		
		List<LeaveRequest> leaveRequest = new ArrayList<LeaveRequest>();
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			
			  String sql = "select * from LeaveRequest where empCode = "+ empCode;
			 
			  
			  SQLQuery query = session.createSQLQuery(sql); query.addEntity(Module.class);
			  leaveRequest = query.list();
			  	
			
			
		} catch (Exception e) {
			//logger.error(e.getMessage(), e);
		}

		return leaveRequest;
		
	}

}
