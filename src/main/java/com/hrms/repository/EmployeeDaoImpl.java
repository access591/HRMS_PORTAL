package com.hrms.repository;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Employee;
import com.hrms.model.EmployeeUtil;
import com.hrms.model.Module;
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

	public List<EmployeeUtil> getAllEmployeesAndArms() {
		List employeeUtils = null;
		
		Session session = this.sessionFactory.getCurrentSession();
		 String sql =" SELECT  DISTINCT  e.EMPLOYEE_CODE,a.ARMS_CODE,e.EMPLOYEE_NAME\r\n"
				+ "				FROM 	M_EMPLOYEE e ,ARMS_LICENSE_DETAILS a where e.EMPLOYEE_CODE= a.EMPLOYEE_CODE";
		
		 SQLQuery query = getSession().createSQLQuery(sql);
		  employeeUtils = query.list();
		 return query.list();
	}

	public List<Employee> getEmployeeByCategoryCode(String categoryCode) {
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Employee e where e.categoryCode = :categoryCode ");
		query.setParameter("categoryCode", categoryCode);
		List<Employee> employeeList = query.list();
		return employeeList;

	}

}
