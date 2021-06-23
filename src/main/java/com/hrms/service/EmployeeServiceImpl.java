package com.hrms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Category;
import com.hrms.model.Employee;
import com.hrms.util.EmployeeUtil;
import com.hrms.repository.EmployeeDao;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeDao employeeDao;
	@Autowired SessionFactory sessionFactory;
	@Autowired CategoryService categoryService;
	
	
	@Override
	public void addEmployee(Employee employee) {
	employee.setEmpCode(employeeDao.getMaxId("EMP"));
	this.employeeDao.saveOrUpdate(employee);
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		List<Employee> listEmployee = employeeDao.findAll();
		return listEmployee;
	}

	@Override
	public Employee findEmployeeById(String id) {
		
		return employeeDao.findById(id);
	}

	@Override
	public void updateEmployee(Employee e) {

	
	this.employeeDao.saveOrUpdate(e);
		
	}

	@Override
	public void removeEmployeet(String id) {
	this.employeeDao.delete(id);
		
	}

	@Override
	public List<Employee> getEmployeeByDeptCode(String deptCode) {
		return this.employeeDao.getEmployeeByDeptCode(deptCode);
	}


	public List<EmployeeUtil> getAllEmployeesAndArms() {
		List<EmployeeUtil> allEmployeesAndArms=employeeDao.getAllEmployeesAndArms();
		return allEmployeesAndArms;
	}
	public List<Employee> getEmployeeByCategoryCode(String categoryCode) {
		
		return this.employeeDao.getEmployeeByCategoryCode(categoryCode);

	}

	@Override
	public List<Employee> findByDateOfJoiningMonth(int month) {
		System.out.println("Service methosd calling");
		return this.employeeDao.findByDateOfJoiningMonth(month);
	}
	@Override
	public List<Employee> findByDepartmentCode(String deptCode) {
		
		return this.employeeDao.findByDepartmentCode(deptCode);
	}

	@Override
	public List<Employee> findByempCode(String empCode) {
		
		return employeeDao.findByIdList(empCode);
	}

	@Override
	public Map<String, Long> countRecordByCategory() {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		Map<String, Long> task4List = new HashMap();
		
		try {
			tx = session.beginTransaction();

			String category = "select e.categoryCode from Employee e group by e.categoryCode";
			Query<String> query = session.createQuery(category);
			List<String>  categoryList = query.list();
			
			String count = "SELECT  COUNT(categoryCode) AS counter FROM Employee e GROUP BY categoryCode";
			Query query1 = session.createQuery(count);
			List<Long>  countList = query1.list();
			
			
		    for (String categoryId : categoryList) {
		       
		         
		         Category cat = categoryService.findCategoryByCatId(categoryId);
		         
		         task4List.put(cat.getCategoryName() ,countList.get(categoryList.indexOf(categoryId)));
		         
		        
		    }
			
			tx.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return task4List;
	}


}
