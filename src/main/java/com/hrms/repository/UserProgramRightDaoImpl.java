package com.hrms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.model.UserRights;
@Repository
public class UserProgramRightDaoImpl implements UserProgramRightDao {
	@Autowired
	SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(UserProgramRightDaoImpl.class.getName());

@Override
public List<UserRights> getAllUserRights() {
		List<UserRights> UserRightsList=null;
		
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(UserRights.class);
			UserRightsList = (List<UserRights>) criteria.setFetchMode("M_URIGHTS", FetchMode.SELECT).list();
		} catch (Exception e) {
			logger.info("UserProgramRightDaoImpl.getAllUserRights" + e.getMessage());
		}
		
		return UserRightsList;
	}

}
