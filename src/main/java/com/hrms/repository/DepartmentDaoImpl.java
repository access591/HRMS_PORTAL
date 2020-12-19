package com.hrms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.model.Department;


@Repository
public class DepartmentDaoImpl implements DepartmentDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addDepartment(Department department) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(department);
		tx.commit();
		session.close();

	}

	@Override
	public List<Department> getAllDepartments() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Department.class);
		List<Department> listDepartment = (List<Department>) criteria.setFetchMode("M_DEPARTMENT", FetchMode.SELECT).list();
		return listDepartment;
	}
	@Override
	public Department findDepartmentById(String id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Department.class);
		Department departmentEdit = (Department) criteria.setFetchMode("M_DEPARTMENT", FetchMode.SELECT)
				.add(Restrictions.eq("Department_Code", id)).uniqueResult();

		return departmentEdit;

	}

	@Override
	public void updateDepartment(Department d) {

		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(d);
		tx.commit();
		session.close();

	}

	@Override
	public void removeDepartment(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Department p = (Department) session.load(Department.class, new String(id));
		System.out.println("value of p " + p);

		session.delete(p);
		tx.commit();
		session.close();

	}

	
}
