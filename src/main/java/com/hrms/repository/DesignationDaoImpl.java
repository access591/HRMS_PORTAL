package com.hrms.repository;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import org.springframework.stereotype.Repository;
import com.hrms.model.Designation;

@Repository
public class DesignationDaoImpl implements DesignationDao {

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;
	
	private Logger logger = LoggerFactory.getLogger(DesignationDaoImpl.class.getName());

	@Override
	public void addDesignation(Designation designation) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.persist(designation);
			tx.commit();
			session.close();
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public List<Designation> getAllDesignations() {
		List<Designation> listDesignation = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Designation.class);
			listDesignation = (List<Designation>) criteria.setFetchMode("M_DESIGNATION", FetchMode.SELECT).list();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return listDesignation;
	}

	@Override
	public Designation findDesignationById(String id) {
		Designation designationEdit = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Designation.class);
			designationEdit = (Designation) criteria.setFetchMode("M_DESIGNATION", FetchMode.SELECT)
					.add(Restrictions.eq("Desg_code", id)).uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return designationEdit;

	}

	@Override
	public void updateDesignation(Designation d) {
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
	public void removeDesignation(String id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			Designation p = (Designation) session.load(Designation.class, new String(id));
			System.out.println("value of p " + p);

			session.delete(p);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public Designation checkDesignationExists(Designation designation) {
		Designation decode = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Designation.class);
			decode = (Designation) criteria.setFetchMode("M_DESIGNATION", FetchMode.SELECT)
					.add(Restrictions.eq("Desg_code", designation.getDesg_code())).uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return decode;
	}

}
