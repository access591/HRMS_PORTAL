package com.hrms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Employee;
@Repository
public class EmployeeDaoImpl  extends AbstractGenericDao<Employee> implements EmployeeDao{

	@Autowired SessionFactory sessionFactory;
	@Override
	public List<Employee> getEmployeeByDeptCode(String deptCode) {
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Employee e where e.departmentCode = :deptCode");
		query.setParameter("deptCode", deptCode);
		//List list = query.list();
		return query.list();
	}
	@Override
	public List<Employee> getEmployeeByCategoryCode(String categoryCode) {
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Employee e where e.categoryCode = :categoryCode ");
		query.setParameter("categoryCode", categoryCode);
		List<Employee> employeeList = query.list();
		return employeeList;
	}
	@Override
	public List<Employee> findByDateOfJoiningMonth(int month) {
		
		try {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT e FROM Employee e WHERE MONTH(e.dateOfJoining) = :month ");
		query.setParameter("month", month);
		List result = query.list();
		return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
