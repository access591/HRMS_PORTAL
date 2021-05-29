package com.hrms.service;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.AttendenceRegister;

@Service
public class AttendenceRegisterServiceImpl implements AttendenceRegisterService{

	@Autowired SessionFactory sessionFactory;
	
	@Override
	public List<AttendenceRegister> findAttendenceByEmpCodeBetweenDate(String empCode,Date fromDate,Date toDate) {
		
		try {
			Session session = sessionFactory.openSession();
			Query<AttendenceRegister> query = session.createQuery("from AttendenceRegister a inner join fetch a.employee e "
					+ "inner join fetch a.department d where e.empCode = :empCode "
					+ "and a.timeIn = :fromDate and a.timeOut =:toDate", AttendenceRegister.class);
			query.setParameter("empCode", empCode);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			List<AttendenceRegister> result = query.getResultList();
			System.out.println("result : "+ result.size());
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AttendenceRegister> findAttendenceByDeptBetweenDate(String deptCode, Date FromDate, Date toDate) {
		try {
			Session session = sessionFactory.openSession();
			Query<AttendenceRegister> query = session.createQuery("from AttendenceRegister a inner join fetch a.employee e "
					+ "inner join fetch a.department d where d.departmentCode = :deptCode "
					+ "and a.timeIn = :fromDate and a.timeOut =:toDate", AttendenceRegister.class);
			query.setParameter("deptCode", deptCode);
			query.setParameter("fromDate", FromDate);
			query.setParameter("toDate", toDate);
			List<AttendenceRegister> result = query.getResultList();
			System.out.println("result : "+ result.size());
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AttendenceRegister> findAllAttendenceBetweenDate(Date FromDate, Date toDate) {
		try {
			Session session = sessionFactory.openSession();
			Query<AttendenceRegister> query = session.createQuery("from AttendenceRegister a inner join fetch a.employee e "
					+ "inner join fetch a.department d "
					+ "and a.timeIn = :fromDate and a.timeOut =:toDate", AttendenceRegister.class);
			
			query.setParameter("fromDate", FromDate);
			query.setParameter("toDate", toDate);
			List<AttendenceRegister> result = query.getResultList();
			System.out.println("result : "+ result.size());
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addAttendenceRegister(AttendenceRegister attn) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		
		// TODO Auto-generated method stub
		s.save(attn);
		s.getTransaction().commit();
		s.clear();
		s.close();
		return true;
	}
	}


