package com.hrms.repository;

import java.util.List;

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
	public List findLeaveGrantByEmp(String empCode) {

		Session session = getSession();
		String hql = "from M_LEAVE_GRANT l where l.empCode=:empCode";
		Query query = session.createQuery(hql);
		query.setParameter("empCode", empCode);
		System.out.println("size session factory : " + query.list().size());

		List leaveGrant = null;
		if (query.list().size() >= 1) {
			leaveGrant = query.list();

		}

		return leaveGrant;
	}
	

}
