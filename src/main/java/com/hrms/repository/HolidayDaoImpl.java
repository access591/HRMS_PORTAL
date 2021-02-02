package com.hrms.repository;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hrms.model.Holiday;
@Repository
public class HolidayDaoImpl implements HolidayDao {

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(HolidayDaoImpl.class.getName());

	@Override
	public void addHoliday(Holiday holiday) {
		try {

			 final  Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.persist(holiday);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public List<Holiday> getAllHolidays() {
		List<Holiday> listHoliday = null;
		try {
			 final  Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Holiday.class);
			listHoliday = (List<Holiday>) criteria.setFetchMode("Holiday_Code", FetchMode.SELECT).list();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return listHoliday;
	}

	@Override
	public Holiday findHolidayById(String id) {
		Holiday editHoliday = null;
		try {

			 final  Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Holiday.class);
			editHoliday = (Holiday) criteria.setFetchMode("Holiday_Code", FetchMode.SELECT)
					.add(Restrictions.eq("Holiday_Code", id)).uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return editHoliday;
	}

	@Override
	public void updateHoliday(Holiday h) {
		try {
			 final  Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(h);
			tx.commit();
			session.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public void removeHoliday(String id) {
		try {

			 final  Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			Holiday H = (Holiday) session.load(Holiday.class, new String(id));
			session.delete(H);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
