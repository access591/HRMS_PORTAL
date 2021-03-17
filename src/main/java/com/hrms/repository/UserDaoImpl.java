package com.hrms.repository;


import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;

import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Login;
import com.hrms.model.UserEntity;

@Repository
public class UserDaoImpl extends AbstractGenericDao<UserEntity> implements UserDao {

	private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class.getName());

	@Override
	public UserEntity findUser(Login login) {
		UserEntity user = null;
		try {

			Criteria criteria = getSession().createCriteria(UserEntity.class);
			user = (UserEntity) criteria.setFetchMode("Myuser", FetchMode.SELECT)
					.add(Restrictions.eq("userCode", login.getUserCode()))
					.add(Restrictions.eq("userPass", login.getUserPassword())).uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return user;

	}

}
