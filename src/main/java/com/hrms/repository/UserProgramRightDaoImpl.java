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

import com.hrms.model.UserRights;
@Repository
public class UserProgramRightDaoImpl implements UserProgramRightDao {
	
	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(UserProgramRightDaoImpl.class.getName());

@Override
public List<UserRights> getAllUserRights() {
	
		List<UserRights> UserRightsList=null;
		
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(UserRights.class);
			UserRightsList = (List<UserRights>) criteria.setFetchMode("M_URIGHTS", FetchMode.SELECT).list();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return UserRightsList;
	}

@Override
public void addUserProgramRight(UserRights userRights) {
	try {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(UserRights.class);
		session.persist(userRights);
		tx.commit();
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
	}

	
}

@Override
public UserRights checkUserRightsExists(UserRights userRights) {
	 UserRights prcode = null;

		try {

			Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(UserRights.class);
			prcode = (UserRights) criteria.setFetchMode("M_URIGHTS", FetchMode.SELECT)
					.add(Restrictions.eq("id", userRights.getPrg_code())).uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return prcode;
}

@Override
public void removeUserProgramRight(long id) {
	try {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		UserRights p = (UserRights) session.load(UserRights.class,(id));
		session.delete(p);
		tx.commit();
		session.close();

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
	}
	
}

@Override
public UserRights findUserRightById(long id) {
	  UserRights userRightsEdit = null;
			try {

				Session session = this.sessionFactory.getCurrentSession();
				Criteria criteria = session.createCriteria(UserRights.class);
				userRightsEdit = (UserRights) criteria.setFetchMode("id", FetchMode.SELECT)
						.add(Restrictions.eq("id", id)).uniqueResult();

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return userRightsEdit;
	  
}

@Override
public void updateUserRights(UserRights ur) {
	try {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(ur);
		tx.commit();
		session.close();

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
	}

	
}

}
