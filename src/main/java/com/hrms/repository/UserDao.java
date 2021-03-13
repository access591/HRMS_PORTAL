package com.hrms.repository;

import com.hrms.dao.GenericDao;
import com.hrms.model.Login;
import com.hrms.model.UserEntity;

public interface UserDao extends GenericDao<UserEntity>{
	
	UserEntity findUser(Login login);



	


}
