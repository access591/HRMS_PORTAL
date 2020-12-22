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
import com.hrms.model.Holiday;
@Repository
public class HolidayDaoImpl implements HolidayDao {

	
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public void addHoliday(Holiday holiday) {
		 Session session = sessionFactory.openSession(); 
		 Transaction tx = session.beginTransaction();
		 session.persist(holiday);
		 tx.commit();
		  session.close();
		
	}

	@Override
	public List<Holiday> getAllHolidays() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Holiday.class);
		List<Holiday> listHoliday = (List<Holiday>) criteria.setFetchMode("Holiday_Code", FetchMode.SELECT).list();
		return listHoliday;
	}

	@Override
	public Holiday findHolidayById(String id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Holiday.class);
		Holiday editHoliday = (Holiday) criteria.setFetchMode("Holiday_Code", FetchMode.SELECT)
				.add(Restrictions.eq("Holiday_Code", id)).uniqueResult();
		return editHoliday;
	}

	@Override
	public void updateHoliday(Holiday h) {
	
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(h);
		tx.commit();
		session.close();	
	}

	@Override
	public void removeHoliday(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Holiday H = (Holiday) session.load(Holiday.class, new String(id));
		session.delete(H);
		tx.commit();
		session.close();	
		
		
	}

}
