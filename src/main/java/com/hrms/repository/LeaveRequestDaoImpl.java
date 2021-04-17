package com.hrms.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.LeaveRequest;
import com.hrms.model.Module;


@Repository
public class LeaveRequestDaoImpl extends AbstractGenericDao<LeaveRequest> implements LeaveRequestDao{

	@Autowired private SessionFactory sessionFactory;
	
	
	@Override
	public List<LeaveRequest> findAllByEmpCode(String empCode) {
		
		System.out.println(" leave request dao Impl model ");
		List<LeaveRequest> leaverequestByEmpCode = new ArrayList<LeaveRequest>();
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			
			String hql = "FROM LeaveRequest E WHERE E.empCode = :empCode";
			Query query = session.createQuery(hql);
			query.setParameter("empCode" , empCode);
			List results = query.list();
		
			leaverequestByEmpCode = results;
		} catch (Exception e) {
			System.out.println("exception block in leaveRequestDaoImpl model ");
			 e.printStackTrace();
		}

		return leaverequestByEmpCode;  
		
	}


	@Override
	public List<LeaveRequest> findByEmpCodeAndApplyDate(String empCode, String applyDate) {
		 Session session = this.sessionFactory.getCurrentSession();
		 String hql = "FROM LeaveRequest e WHERE e.empCode=:empCode and e.applyDate=:applyDate";
		 Query query = session.createQuery(hql);
		 query.setParameter("empCode", empCode);
		 query.setParameter("applyDate", applyDate);
		 List result = query.list();
		return result;
	}


	@Override
	public List<LeaveRequest> findAllByDeptCodeAndStatus(String deptCode) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from LeaveRequest e where e.deptCode = :deptCode and e.status = 'N' ";
		Query query = session.createQuery(hql);
		query.setParameter("deptCode", deptCode);
		List result = query.list();
		return result;
	}


	@Override
	public List<LeaveRequest> getEmployeeByStatusY() {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from LeaveRequest e where e.status = 'Y'";
		Query query = session.createQuery(hql);
		List result = query.list();
		return result;
	}


}
