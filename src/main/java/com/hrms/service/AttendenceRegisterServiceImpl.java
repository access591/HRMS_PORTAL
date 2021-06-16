package com.hrms.service;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.AttendenceRegister;
import com.hrms.model.MedicalReimbursement;
import com.hrms.repository.AttendenceRegisterDao;

@Service
public class AttendenceRegisterServiceImpl implements AttendenceRegisterService{

	@Autowired SessionFactory sessionFactory;
	@Autowired AttendenceRegisterDao attendenceRegisterDao;
	Session session=null;
	@Override
	public List<AttendenceRegister> findAttendenceByEmpCodeBetweenDate(String empCode,Date fromDate,Date toDate) {
		List<AttendenceRegister> result=null;
		Query<AttendenceRegister> query=null;
		try {
			 session = sessionFactory.openSession();
		      query = session.createQuery("from AttendenceRegister a inner join fetch a.employee e "
					+ "inner join fetch a.department d where e.empCode = :empCode "
					+ "and a.timeIn = :fromDate and a.timeOut =:toDate", AttendenceRegister.class);
			query.setParameter("empCode", empCode);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			result = query.getResultList();
		
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AttendenceRegister> findAttendenceByDeptBetweenDate(String deptCode, Date fromDate, Date toDate) {
		List<AttendenceRegister> result=null;
		try {
		 session = sessionFactory.openSession();
			Query<AttendenceRegister> query = session.createQuery("from AttendenceRegister a inner join fetch a.employee e "
					+ "inner join fetch a.department d where d.departmentCode = :deptCode "
					+ "and a.timeIn = :fromDate and a.timeOut =:toDate", AttendenceRegister.class);
			query.setParameter("deptCode", deptCode);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			result = query.getResultList();
		
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AttendenceRegister> findAllAttendenceBetweenDate(Date fromDate, Date toDate) {
		List<AttendenceRegister> result=null;
		try {
	 session = sessionFactory.openSession();
			Query<AttendenceRegister> query = session.createQuery("from AttendenceRegister a inner join fetch a.employee e "
					+ "inner join fetch a.department d "
					+ "where a.attendenceDate >= :fromDate and a.attendenceDate <=:toDate", AttendenceRegister.class);
			
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		result = query.getResultList();
		
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addAttendenceRegister(AttendenceRegister attn) {
		session=sessionFactory.openSession();
		session.beginTransaction();
		
		
		session.save(attn);
		session.getTransaction().commit();
		session.clear();
		session.close();
		return true;
	}

	@Override
	public List<AttendenceRegister> getAllAttendenceRegister() {
	
		return attendenceRegisterDao.findAll();
	}


	public AttendenceRegister findAttendenceRegisterByEmpCode(String empCode) {

		try {
			session = sessionFactory.openSession();
			Query<AttendenceRegister> query = session.createQuery(
					"from AttendenceRegister a inner join fetch a.employee e " + "where e.empCode = :empCode",
					AttendenceRegister.class);

			query.setParameter("empCode", empCode);

			AttendenceRegister result = query.getSingleResult();
			System.out.println("result : ");
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AttendenceRegister> findAttendenceByEmpStatusAbsent(String empCode) {
		try {
			session = sessionFactory.openSession();
			Query<AttendenceRegister> query = session
					.createQuery("from AttendenceRegister a inner join fetch a.employee e "
							+ "where e.empCode = :empCode and a.status= :status", AttendenceRegister.class);

			query.setParameter("empCode", empCode);
			query.setParameter("status", "Absent");

			List<AttendenceRegister> result = query.getResultList();
			System.out.println("result : " + result.size());
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void removeAttendanceRegister(int id) {
		session = sessionFactory.openSession();
		Object o = session.get(AttendenceRegister.class, id);
		AttendenceRegister e = (AttendenceRegister) o;
		Transaction tx = session.beginTransaction();
		session.delete(e);
		tx.commit();
		session.close();

	}

	@Override
	public List<AttendenceRegister> findAttendenceStatusByDeptCode(String deptCode, Date fromDate, Date toDate) {
		List<AttendenceRegister> result = null;
		try {

			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query<AttendenceRegister> query = session
					.createQuery("from AttendenceRegister a inner join fetch a.department e "
							+ "where e.departmentCode = :deptCode and a.status= :status and a.attendenceDate>=:fromDate and "
							+ "a.attendenceDate<=:toDate", AttendenceRegister.class);
			query.setParameter("status", "Y");
			query.setParameter("deptCode", deptCode);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			tx.commit();
			result = query.getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

