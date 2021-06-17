package com.hrms.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Employee;
import com.hrms.util.EmployeeUtil;
import com.hrms.model.Module;
@Repository
public class EmployeeDaoImpl  extends AbstractGenericDao<Employee> implements EmployeeDao{

	@Autowired SessionFactory sessionFactory;
	Session session=null;
	Query query =null;
	@Override
	public List<Employee> getEmployeeByDeptCode(String deptCode) {
		
		 try {
			session = this.sessionFactory.getCurrentSession();
			 query = session.createQuery("from Employee e where e.departmentCode = :deptCode");
			query.setParameter("deptCode", deptCode);
			
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return null; 
	}
	@Override

	public List<EmployeeUtil> getAllEmployeesAndArms() {
		List employeeUtils = null;
		 String sql=null;
		try {
			 session = this.sessionFactory.getCurrentSession();
			  sql =" SELECT  DISTINCT  e.EMPLOYEE_CODE,a.ARMS_CODE,e.EMPLOYEE_NAME,e.CATEGORY_CODE,e.DEPARTMENT_CODE,e.DESIGNATION_CODE,e.EMP_IMG\r\n"
					+"FROM M_EMPLOYEE e ,ARMS_LICENSE_DETAILS a where e.EMPLOYEE_CODE= a.EMPLOYEE_CODE";
			
			 SQLQuery query = getSession().createSQLQuery(sql);
			  employeeUtils = query.list();
			 return employeeUtils;
		} catch (HibernateException e) {
			
			e.printStackTrace();
		}
		 return null;
	}

	public List<Employee> getEmployeeByCategoryCode(String categoryCode) {
	
		List<Employee> employeeList=null;
		 try {
			session = this.sessionFactory.getCurrentSession();
			 query = session.createQuery("from Employee e where e.categoryCode = :categoryCode ");
			query.setParameter("categoryCode", categoryCode);
			employeeList = query.list();
			return employeeList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return null;
	}
	@Override
	public List<Employee> findByDateOfJoiningMonth(int month) {
		
		try {
		session = this.sessionFactory.getCurrentSession();
		 query = session.createQuery("SELECT e FROM Employee e WHERE MONTH(e.dateOfJoining) = :month ");
		query.setParameter("month", month);
		List result = query.list();
		return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Employee> findByDepartmentCode(String deptCode) {
		 session = this.sessionFactory.getCurrentSession();
		 query = session.createQuery("from Employee e where e.departmentCode = :deptCode ");
		query.setParameter("deptCode", deptCode);
		List<Employee> employeeList = query.list();
		return employeeList;
	}
	@Override
	public List<Employee> findByIdList(String empCode) {
	session = this.sessionFactory.getCurrentSession();
		 query = session.createQuery("from Employee e where e.empCode = :empCode ");
		query.setParameter("empCode", empCode);
		List<Employee> employeeList = query.list();
		return employeeList;
	}


}
