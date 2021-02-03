package com.hrms.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.UserRights;
@Repository
public class UserProgramRightDaoImpl extends AbstractGenericDao<UserRights> implements UserProgramRightDao {

	private Logger logger = LoggerFactory.getLogger(UserProgramRightDaoImpl.class.getName());



}
