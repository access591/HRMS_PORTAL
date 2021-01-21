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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hrms.model.Grade;

@Repository
public class GradeMaterDaoImpl implements GradeMaterDao {
	@Autowired
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(GradeMaterDaoImpl.class.getName());

	@Override
	public void addGrade(Grade grade) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.persist(grade);
			tx.commit();
			session.close();

		} catch (Exception e) {
			logger.info("GradeMaterDaoImpl.addGrade" + e.getMessage());
		}

	}

	@Override
	public List<Grade> getAllGrades() {
		List<Grade> listGrade = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Grade.class);
			listGrade = (List<Grade>) criteria.setFetchMode("M_Grade", FetchMode.SELECT).list();

		} catch (Exception e) {
			logger.info("GradeMaterDaoImpl.getAllGrades" + e.getMessage());
		}
		return listGrade;

	}

	@Override
	public Grade findGradeById(String id) {
		Grade gradeEdit = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Grade.class);
			gradeEdit = (Grade) criteria.setFetchMode("M_Grade", FetchMode.SELECT)
					.add(Restrictions.eq("Grade_Code", id)).uniqueResult();

		} catch (Exception e) {
			logger.info("GradeMaterDaoImpl.findGradeById" + e.getMessage());
		}

		return gradeEdit;

	}

	@Override
	public void updateGrade(Grade G) {
		try {

			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(G);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("GradeMaterDaoImpl.updateGrade" + e.getMessage());

		}

	}

	@Override
	public void removeGrade(String id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			Grade g = (Grade) session.load(Grade.class, new String(id));
			System.out.println("value of G " + g);

			session.delete(g);
			tx.commit();
			session.close();

		} catch (Exception e) {

			logger.info("GradeMaterDaoImpl.removeGrade" + e.getMessage());

		}

	}

	@Override
	public Grade checkGradeExists(Grade grade) {
		Grade gecode = null;

		try {

			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Grade.class);
			gecode = (Grade) criteria.setFetchMode("M_Grade", FetchMode.SELECT)
					.add(Restrictions.eq("Grade_Code", grade.getGrade_Code())).uniqueResult();

		} catch (Exception e) {
			logger.info("GradeMaterDaoImpl.checkGradeExists" + e.getMessage());
		}
		return gecode;
	}

}
