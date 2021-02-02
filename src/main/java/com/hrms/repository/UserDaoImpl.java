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


import com.hrms.model.Login;
import com.hrms.model.UserEntity;

@Repository
public class UserDaoImpl implements UserDao {
	
	
	private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class.getName());
	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;
	@Override
	public UserEntity findUser(Login login) {
		UserEntity user = null;
		try {

			Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(UserEntity.class);
			user = (UserEntity) criteria.setFetchMode("Myuser", FetchMode.SELECT)
					.add(Restrictions.eq("userCode", login.getUserCode()))
					.add(Restrictions.eq("userPass", login.getUserPassword())).uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		
		return user;

	}

	@Override
	public List<UserEntity> getAllUsers() {
		List<UserEntity> listUser = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(UserEntity.class);
			listUser = (List<UserEntity>) criteria.setFetchMode("Myuser", FetchMode.SELECT).list();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return listUser;
	}

	@Override
	public UserEntity findDataById(String id) {
		UserEntity userRecord = null;

		try {
			Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(UserEntity.class);
			userRecord = (UserEntity) criteria.setFetchMode("Myuser", FetchMode.SELECT)
					.add(Restrictions.eq("userCode", id)).uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return userRecord;
	}

	@Override
	public void addUser(UserEntity userEntity) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(UserEntity.class);
			session.persist(userEntity);
			tx.commit();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public UserEntity checkUserExistsOrNot(UserEntity userEntity) {
		UserEntity usercode = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(UserEntity.class);
			usercode = (UserEntity) criteria.setFetchMode("Myuser", FetchMode.SELECT)
					.add(Restrictions.eq("userCode", userEntity.getUserCode())).uniqueResult();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return usercode;
	}

	@Override
	public void removeUser(String id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			UserEntity u = (UserEntity) session.load(UserEntity.class, new String(id));
			session.delete(u);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public UserEntity findUserById(String id) {
		UserEntity userEdit = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(UserEntity.class);
			userEdit = (UserEntity) criteria.setFetchMode("Myuser", FetchMode.SELECT)
					.add(Restrictions.eq("userCode", id)).uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return userEdit;
	}

	@Override
	public void updateUser(UserEntity u) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(u);
			tx.commit();
			session.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}

