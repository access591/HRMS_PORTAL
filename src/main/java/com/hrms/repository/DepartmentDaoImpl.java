package com.hrms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.model.Department;
import com.hrms.model.SubModule;


@Repository
public class DepartmentDaoImpl implements DepartmentDao {
	@Autowired
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(BankDaoImpl.class.getName());
	@Override
	public void addDepartment(Department department) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.persist(department);
			tx.commit();
			session.close();

		} catch (Exception e) {
			logger.info("DepartmentDaoImpl.addDepartment" + e.getMessage());
		}

	}

	@Override
	public List<Department> getAllDepartments() {
		List<Department> listDepartment = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Department.class);
			listDepartment = (List<Department>) criteria.setFetchMode("M_DEPARTMENT", FetchMode.SELECT).list();
		} catch (Exception e) {
			logger.info("DepartmentDaoImpl.getAllDepartments" + e.getMessage());
		}
		return listDepartment;
	}

	@Override
	public Department findDepartmentById(String id) {
		Department departmentEdit = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Department.class);
			departmentEdit = (Department) criteria.setFetchMode("M_DEPARTMENT", FetchMode.SELECT)
					.add(Restrictions.eq("Department_Code", id)).uniqueResult();
		} catch (Exception e) {
			logger.info("DepartmentDaoImpl.findDepartmentById" + e.getMessage());
		}
		return departmentEdit;

	}

	@Override
	public void updateDepartment(Department d) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(d);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("DepartmentDaoImpl.updateDepartment" + e.getMessage());
		}
	}

	@Override
	public void removeDepartment(String id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			Department p = (Department) session.load(Department.class, new String(id));

			session.delete(p);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("DepartmentDaoImpl.removeDepartment" + e.getMessage());
		}

	}

	@Override
	public Department checkDepartmentExists(Department department) {
		Department decode = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Department.class);
			decode = (Department) criteria.setFetchMode("M_DEPARTMENT", FetchMode.SELECT)
					.add(Restrictions.eq("Department_Code", department.getDepartment_Code())).uniqueResult();

		} catch (Exception e) {
			logger.info("DepartmentDaoImpl.checkDepartmentExists" + e.getMessage());
		}

		return decode;
	}
}
