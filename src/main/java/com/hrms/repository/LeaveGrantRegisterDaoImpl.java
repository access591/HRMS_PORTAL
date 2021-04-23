package com.hrms.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.LeaveGrant;
import org.hibernate.query.Query;


@Repository
public class LeaveGrantRegisterDaoImpl extends AbstractGenericDao<LeaveGrant> implements LeaveGrantRegisterDao {

	@Autowired SessionFactory sessionFactory;
	
	@Override
	public LeaveGrant findLeaveGrantByEmp(String empCode) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from LeaveGrant l where l.empCode=:empCode";
		Query query = session.createQuery(hql);
		query.setParameter("empCode", empCode);
		
		LeaveGrant leaveGrant = null;
		if(query.list().size()>=1) {
			leaveGrant = (LeaveGrant) query.list().get(0);
			return leaveGrant;
		}
		
		return null;
	}
	

}
