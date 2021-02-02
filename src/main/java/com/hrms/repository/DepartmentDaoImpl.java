package com.hrms.repository;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hrms.model.Department;


@Repository
public class DepartmentDaoImpl implements DepartmentDao {
	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(BankDaoImpl.class.getName());
	@Override
	public void addDepartment(Department department) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.persist(department);
			tx.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public List<Department> getAllDepartments() {
		List<Department> listDepartment = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Department.class);
			listDepartment = (List<Department>) criteria.setFetchMode("M_DEPARTMENT", FetchMode.SELECT).list();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return listDepartment;
	}

	@Override
	public Department findDepartmentById(String id) {
		Department departmentEdit = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Department.class);
			departmentEdit = (Department) criteria.setFetchMode("M_DEPARTMENT", FetchMode.SELECT)
					.add(Restrictions.eq("Department_Code", id)).uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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
			logger.error(e.getMessage(), e);
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
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public Department checkDepartmentExists(Department department) {
		Department decode = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Department.class);
			decode = (Department) criteria.setFetchMode("M_DEPARTMENT", FetchMode.SELECT)
					.add(Restrictions.eq("Department_Code", department.getDepartment_Code())).uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return decode;
	}
}
