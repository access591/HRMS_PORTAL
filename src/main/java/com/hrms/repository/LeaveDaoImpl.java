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

import com.hrms.model.Leave;

@Repository
public class LeaveDaoImpl implements LeaveDao {
	@Autowired
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(LeaveDaoImpl.class.getName());

	@Override
	public void addLeave(Leave leave) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.persist(leave);
			tx.commit();
			session.close();

		} catch (Exception e) {
			logger.info("LeaveDaoImpl.addLeave" + e.getMessage());
		}

	}

	@Override
	public List<Leave> getAllLeaves() {
		List<Leave> listLeave = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Leave.class);
			listLeave = (List<Leave>) criteria.setFetchMode("M_LEAVE", FetchMode.SELECT).list();

		} catch (Exception e) {
			logger.info("LeaveDaoImpl.getAllLeaves" + e.getMessage());
		}
		return listLeave;
	}

	@Override
	public Leave findLeaveById(String id) {
		Leave leaveEdit = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Leave.class);
			leaveEdit = (Leave) criteria.setFetchMode("M_LEAVE", FetchMode.SELECT).add(Restrictions.eq("Lev_code", id))
					.uniqueResult();

		} catch (Exception e) {
			logger.info("LeaveDaoImpl.findLeaveById" + e.getMessage());
		}

		return leaveEdit;

	}

	@Override
	public void updateLeave(Leave d) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(d);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.info("LeaveDaoImpl.updateLeave" + e.getMessage());
		}

	}

	@Override
	public void removeLeave(String id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			Leave p = (Leave) session.load(Leave.class, new String(id));
			session.delete(p);
			tx.commit();
			session.close();

		} catch (Exception e) {
			logger.info("LeaveDaoImpl.removeLeave" + e.getMessage());
		}

	}

}
