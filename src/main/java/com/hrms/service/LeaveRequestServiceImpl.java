package com.hrms.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.LeaveRequest;
import com.hrms.repository.LeaveRequestDao;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

	@Autowired
	LeaveRequestDao leaveRequestDao;
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<LeaveRequest> findAllByEmpCode(String empCode) {

		return this.leaveRequestDao.findAllByEmpCode(empCode);

	}

	@Override
	public void addLeave(LeaveRequest leaveRequest) {

		if (leaveRequest.getRequestType().equals("single")) {
			leaveRequest.setToDate(leaveRequest.getFromDate());
		}
		this.leaveRequestDao.saveOrUpdate(leaveRequest);

	}

	@Override
	public List<LeaveRequest> getAllLeaves() {
		// System.out.println("DAO MODEL :
		// "+this.leaveRequestDao.findAll().get(1).getEmpName());
		System.out.println("hii getAll leaves ");
		try {

			Session session = sessionFactory.openSession();
			Query<LeaveRequest> query = session.createQuery(
					"from LeaveRequest lr inner join fetch lr.employee e" + " inner join fetch lr.leave lv ",
					LeaveRequest.class);

			List<LeaveRequest> leaveRequest = query.getResultList();

			System.out.println("hii getAll leaves " + leaveRequest.size());

			return leaveRequest;

		} catch (Exception e) {
			System.out.println("error occured in gt All leaves ");
			e.printStackTrace();
		}
		return null;
		// return this.leaveRequestDao.findAll();
	}

	@Override
	public LeaveRequest findLeaveRequestById(long id) {

		return this.leaveRequestDao.findById(id);
	}

	@Override
	public void updateLeaveRequest(LeaveRequest d) {
		this.leaveRequestDao.saveOrUpdate(d);

	}

	@Override
	public void removeLeaveRequest(Long id) {
		this.leaveRequestDao.delete(id);

	}

	@Override
	public List<LeaveRequest> findByEmpCodeAndApplyDate(String empCode, String applyDate) {

		return this.leaveRequestDao.findByEmpCodeAndApplyDate(empCode, applyDate);
	}

	@Override
	public List<LeaveRequest> findAllByDeptCodeAndStatusN(String deptCode) {
		return this.leaveRequestDao.findAllByDeptCodeAndStatusN(deptCode);
	}

	@Override
	public List<LeaveRequest> getEmployeeByStatusY() {

		return this.leaveRequestDao.getEmployeeByStatusY();
	}

	@Override
	public List<LeaveRequest> findByEmpBetweenDate(String empCode, Date toDate, Date fromDate) {

		return this.leaveRequestDao.findByEmpBetweenDate(empCode, toDate, fromDate);
	}

	@Override
	public LeaveRequest findByToDate(Date date) {
		// TODO Auto-generated method stub
		return this.leaveRequestDao.findByToDate(date);
	}

	@Override
	public List<LeaveRequest> getEmployeeByStatusN() {
		return this.leaveRequestDao.getEmployeeByStatusN();
	}

	@Override
	public List<LeaveRequest> findAllApproveLeaveRequestBetweenDate(Date fromDate, Date toDate) {
		
		try {
			Session session = sessionFactory.openSession();
			Query<LeaveRequest> query = session.createQuery("from LeaveRequest l "
					+ "where l.fromDate>=:fromDate and l.toDate<=:toDate and"
					+ " l.status=:status", LeaveRequest.class);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			query.setParameter("status", "Y");
			
			List<LeaveRequest> result = query.getResultList();
			System.out.println("leave request size====>"+result.size());
			return result;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<LeaveRequest> findApproveLeaveRequestByEmpBetweenDate(Date fromDate, Date toDate, String empCode) {
		try {
			Session session = sessionFactory.openSession();
			Query<LeaveRequest> query = session.createQuery("from LeaveRequest l left join fetch "
					+ "l.employee e "
					+ "where l.fromDate>=:fromDate and l.toDate<=:toDate and"
					+ " l.status=:status and e.empCode = :empCode", LeaveRequest.class);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			query.setParameter("status", "Y");
			query.setParameter("empCode", empCode);
			
			List<LeaveRequest> result = query.getResultList();
			System.out.println("leave request size====>"+result.size());
			return result;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<LeaveRequest> findAllApproveLeaveRequestByDeptBetweenDate(Date fromDate, Date toDate, String deptCode) {
		try {
			Session session = sessionFactory.openSession();
			Query<LeaveRequest> query = session.createQuery("from LeaveRequest l left join fetch "
					+ "l.department d "
					+ "where l.fromDate>=:fromDate and l.toDate<=:toDate and"
					+ " l.status=:status and d.departmentCode = :deptCode", LeaveRequest.class);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			query.setParameter("status", "Y");
			query.setParameter("deptCode", deptCode);
			
			List<LeaveRequest> result = query.getResultList();
			System.out.println("leave request size====>"+result.size());
			return result;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
