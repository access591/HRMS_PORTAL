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
import com.hrms.model.Holiday;
@Repository
public class HolidayDaoImpl implements HolidayDao {

	@Autowired
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(HolidayDaoImpl.class.getName());

	@Override
	public void addHoliday(Holiday holiday) {
		try {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.persist(holiday);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("HolidayDaoImpl.addHoliday" + e.getMessage());
		}

	}

	@Override
	public List<Holiday> getAllHolidays() {
		List<Holiday> listHoliday = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Holiday.class);
			listHoliday = (List<Holiday>) criteria.setFetchMode("Holiday_Code", FetchMode.SELECT).list();

		} catch (Exception e) {
			logger.info("HolidayDaoImpl.getAllHolidays" + e.getMessage());
		}
		return listHoliday;
	}

	@Override
	public Holiday findHolidayById(String id) {
		Holiday editHoliday = null;
		try {

			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Holiday.class);
			editHoliday = (Holiday) criteria.setFetchMode("Holiday_Code", FetchMode.SELECT)
					.add(Restrictions.eq("Holiday_Code", id)).uniqueResult();

		} catch (Exception e) {
			logger.info("HolidayDaoImpl.findHolidayById" + e.getMessage());
		}
		return editHoliday;
	}

	@Override
	public void updateHoliday(Holiday h) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(h);
			tx.commit();
			session.close();

		} catch (Exception e) {
			logger.info("HolidayDaoImpl.updateHoliday" + e.getMessage());
		}

	}

	@Override
	public void removeHoliday(String id) {
		try {

			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			Holiday H = (Holiday) session.load(Holiday.class, new String(id));
			session.delete(H);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("HolidayDaoImpl.removeHoliday" + e.getMessage());
		}

	}

}
