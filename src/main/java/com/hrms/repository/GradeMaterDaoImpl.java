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

import com.hrms.model.Grade;

@Repository
public class GradeMaterDaoImpl implements GradeMaterDao {
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public void addGrade(Grade grade) {

	  Session session = sessionFactory.openSession(); 
		 Transaction tx = session.beginTransaction();
		 session.persist(grade);
		 tx.commit();
		  session.close();
		 
		
	}
	@Override
	public List<Grade> getAllGrades() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Grade.class);
		List<Grade> listGrade = (List<Grade>) criteria.setFetchMode("M_Grade", FetchMode.SELECT).list();
		return listGrade;
		
		
	
	}
	@Override
	public Grade findGradeById(String id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Grade.class);
		Grade gradeEdit = (Grade) criteria.setFetchMode("M_Grade", FetchMode.SELECT)
				.add(Restrictions.eq("Grade_Code", id)).uniqueResult();

		return gradeEdit;

	}
	@Override
	public void updateGrade(Grade G) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(G);
		tx.commit();
		session.close();	
		
	}
	@Override
	public void removeGrade(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Grade g = (Grade) session.load(Grade.class, new String(id));
		System.out.println("value of G " + g);

		session.delete(g);
		tx.commit();
		session.close();	
		
	}
	@Override
	public Grade checkGradeExists(Grade grade) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Grade.class);
		Grade gecode = (Grade) criteria.setFetchMode("M_Grade",FetchMode.SELECT)
				.add(Restrictions.eq("Grade_Code", grade.getGrade_Code())).uniqueResult();
		return gecode;	}

}
