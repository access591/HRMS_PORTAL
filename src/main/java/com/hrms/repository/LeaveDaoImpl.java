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

import com.hrms.model.Leave;

@Repository
public class LeaveDaoImpl implements LeaveDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addLeave(Leave leave) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(leave);
		tx.commit();
		session.close();

	}

	@Override
	public List<Leave> getAllLeaves() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Leave.class);
		List<Leave> listLeave = (List<Leave>) criteria.setFetchMode("M_LEAVE", FetchMode.SELECT).list();
		return listLeave;
	}

	@Override
	public Leave findLeaveById(String id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Leave.class);
		Leave leaveEdit = (Leave) criteria.setFetchMode("M_LEAVE", FetchMode.SELECT)
				.add(Restrictions.eq("Lev_code", id)).uniqueResult();

		return leaveEdit;

	}

	@Override
	public void updateLeave(Leave d) {

		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(d);
		tx.commit();
		session.close();

	}

	@Override
	public void removeLeave(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Leave p = (Leave) session.load(Leave.class, new String(id));
		System.out.println("value of p " + p);

		session.delete(p);
		tx.commit();
		session.close();

	}

}
