package com.hrms.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.ArmsLicenseDetails;
import com.hrms.model.Employee;
@Repository
public class ArmsLicenseDaoImpl  extends AbstractGenericDao<ArmsLicenseDetails> implements ArmsLicenseDao {
	@Autowired SessionFactory sessionFactory;
	@Override
	public  ArmsLicenseDetails findArmsByEmpEmpCode(String empCode) {
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ArmsLicenseDetails e where e.empCode=:empCode");
		query.setParameter("empCode",empCode);
		List<ArmsLicenseDetails> employeeList = query.list();
		
		if(employeeList!=null)
		{
		return employeeList.get(0);
		}
		
		return null;
	}

}

