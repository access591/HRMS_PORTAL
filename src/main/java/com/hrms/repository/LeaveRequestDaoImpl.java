package com.hrms.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.LeaveRequest;


@Repository
public class LeaveRequestDaoImpl extends AbstractGenericDao<LeaveRequest> implements LeaveRequestDao{

	@Autowired private SessionFactory sessionFactory;
	
	
	@Override
	public List<LeaveRequest> findAllByEmpCode(String empCode) {
		
		System.out.println(" leave request dao Impl model ");
		List<LeaveRequest> leaverequestByEmpCode = new ArrayList<LeaveRequest>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			
			String hql = "FROM LeaveRequest l left join fetch l.employee E WHERE E.empCode = :empCode";
			Query<LeaveRequest> query = session.createQuery(hql,LeaveRequest.class);
			query.setParameter("empCode" , empCode);
			leaverequestByEmpCode = query.getResultList();
		
			System.out.println("leave request dao impl is completed");
			return leaverequestByEmpCode;
		} catch (Exception e) {
			System.out.println("exception block in leaveRequestDaoImpl model ");
			 e.printStackTrace();
		}

		return null;  
		
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
	public List<LeaveRequest> findAllByDeptCodeAndStatusN(String deptCode) {
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


	@Override
	public List<LeaveRequest> findByEmpBetweenDate(String empCode, Date toDate, Date fromDate) {
		
		List<LeaveRequest> listLeaveRequest = null;
		try {
		Session session = sessionFactory.openSession();
		
		String qu = "FROM LeaveRequest l inner join fetch l.employee e "
				+ "WHERE l.fromDate >=:fromDate and l.toDate <=:toDate and  e.empCode =:empCode";
		Query<LeaveRequest> query = session.createQuery(qu,LeaveRequest.class);
		
		System.out.println("query set : = " + query.toString());
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		query.setParameter("empCode", empCode);
		listLeaveRequest = query.list();
		return listLeaveRequest;
		}catch(Exception e) {
			System.out.println("findByEmpBetweenDate error ");
			e.printStackTrace();
			
		}
		
		return listLeaveRequest;
	}


	@Override
	public LeaveRequest findByToDate(Date date) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<LeaveRequest> query = session.createQuery("from LeaveRequest l where l.toDate =:date", LeaveRequest.class);
		query.setParameter("date", date);
		List<LeaveRequest> list = query.list();
		return list.get(0);
	}


	@Override
	public List<LeaveRequest> getEmployeeByStatusN() {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from LeaveRequest e where e.status = 'N'";
		Query query = session.createQuery(hql);
		List result = query.list();
		return result;
	}


}
