package com.hrms.repository;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.criterion.Restrictions;


import org.springframework.stereotype.Repository;
import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Department;
@Repository
public class DepartmentDaoImpl extends AbstractGenericDao<Department> implements DepartmentDao {

	private Logger logger = LoggerFactory.getLogger(DepartmentDaoImpl.class.getName());
	@Override
	public Department checkDepartmentExists(Department department) {
		Department deptName = null;
		try {
			
			Criteria criteria = getSession().createCriteria(Department.class);
			deptName = (Department) criteria.setFetchMode("DEP_NAME", FetchMode.SELECT)
					.add(Restrictions.eq("deptName", department.getDeptName())).uniqueResult();

		} catch (Exception e) {
			logger.info("DepartmentDaoImpl.checkDepartmentExists" + e.getMessage());
		}

		return deptName;
	}

	
	
}

