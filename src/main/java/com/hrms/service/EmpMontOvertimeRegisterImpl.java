package com.hrms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.EmpMonOvertime;

@Service
public class EmpMontOvertimeRegisterImpl implements EmpMontOvertimeRegister{

	@Autowired SessionFactory sessionFactory;
	
	@Override
	public List<EmpMonOvertime> findEmpMontOvertimeByEmpCode(String empCode) {
		
		try {
			Session session = sessionFactory.openSession();
			Query<EmpMonOvertime> query = session.createQuery("from EmpMonOvertime em inner join fetch em.employee e "
					+ "where e.empCode = :empCode", EmpMonOvertime.class);
			query.setParameter("empCode", empCode);
			List<EmpMonOvertime> result = query.getResultList();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public List<EmpMonOvertime> findAllEmpMontOvertime() {
		
		try {
			Session session = sessionFactory.openSession();
			Query<EmpMonOvertime> query = session.createQuery("from EmpMonOvertime em inner join fetch em.employee e "
					, EmpMonOvertime.class);
			
			List<EmpMonOvertime> result = query.getResultList();
			System.out.println("result size : "+result.size());
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EmpMonOvertime> findAllByDeptCode(String deptCode) {
		try {
			Session session = sessionFactory.openSession();
			Query<EmpMonOvertime> query = session.createQuery("from EmpMonOvertime em inner join fetch em.employee e "
					+ "where e.departmentCode = :deptCode"
					, EmpMonOvertime.class);
			query.setParameter("deptCode", deptCode);
			List<EmpMonOvertime> result = query.getResultList();
			System.out.println("result size : "+result.size());
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EmpMonOvertime> findAllDeptByMonth(int month) {
		try {
			Session session = sessionFactory.openSession();
			Query<EmpMonOvertime> query = session.createQuery("from EmpMonOvertime em WHERE MONTH(em.oTimeMonth) = :month" 
					, EmpMonOvertime.class);
			query.setParameter("month", month);
			List<EmpMonOvertime> result = query.getResultList();
			System.out.println("result size : "+result.size());
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public List<EmpMonOvertime> findOvertimeMonthByDeptCodeAndMonth(String deptCode, int month) {
		try {
			Session session = sessionFactory.openSession();
			Query<EmpMonOvertime> query = session.createQuery("from EmpMonOvertime em "
					+ "inner join fetch em.employee e WHERE e.departmentCode = :deptCode and MONTH(em.oTimeMonth) = :month" 
					, EmpMonOvertime.class);
			query.setParameter("month", month);
			query.setParameter("deptCode", deptCode);
			
			List<EmpMonOvertime> result = query.getResultList();
			System.out.println("result size : "+result.size());
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//SELECT e FROM Employee e WHERE MONTH(e.oTimeMonth) = :month

}
