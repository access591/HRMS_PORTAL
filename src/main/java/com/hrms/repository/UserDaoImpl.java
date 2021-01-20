package com.hrms.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.model.Login;
import com.hrms.model.UserEntity;

@Repository
public class UserDaoImpl implements UserDao {
	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public UserEntity findUser(Login login) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(UserEntity.class);
		UserEntity user = (UserEntity) criteria.setFetchMode("Myuser", FetchMode.SELECT)
				.add(Restrictions.eq("userCode", login.getUserCode()))
				.add(Restrictions.eq("userPass", login.getUserPassword())).uniqueResult();
		        logger.info("User Login : {}",user);
		return user;

	}

	@Override
	public List<UserEntity> getAllUsers() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(UserEntity.class);
		List<UserEntity> listUser = (List<UserEntity>) criteria.setFetchMode("Myuser", FetchMode.SELECT).list();
		 logger.info("User list : {}",listUser);
		return listUser;
	}

	@Override
	public UserEntity findDataById(String id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(UserEntity.class);
		UserEntity userRecord = (UserEntity) criteria.setFetchMode("Myuser", FetchMode.SELECT)
				.add(Restrictions.eq("userCode", id)).uniqueResult();
		 logger.info("User Record : {}",userRecord);
		return userRecord;
	}

}
