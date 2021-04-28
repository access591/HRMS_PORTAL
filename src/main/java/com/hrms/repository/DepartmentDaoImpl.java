package com.hrms.repository;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Department;
@Repository
public class DepartmentDaoImpl extends AbstractGenericDao<Department> implements DepartmentDao {

	private Logger logger = LoggerFactory.getLogger(DepartmentDaoImpl.class.getName());
	@Autowired SessionFactory sessionFactory;
	
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
	@Override
	public List<Department> findByDepartmentByDeptCode(String deptCode) {
		Session session = sessionFactory.getCurrentSession();
		Query<Department> query = session.createQuery("from Department d where d.empCode = :empCode");
		query.setParameter("empCode", deptCode);
		List<Department> department = query.list();
		return department;
	}

	
	
}

