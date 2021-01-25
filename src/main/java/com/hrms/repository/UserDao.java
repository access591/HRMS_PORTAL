package com.hrms.repository;

import java.util.List;
import com.hrms.model.Login;
import com.hrms.model.UserEntity;

public interface UserDao {
	
	UserEntity findUser(Login login);

	List<UserEntity> getAllUsers();

	UserEntity findDataById(String id);

	public void addUser(UserEntity userEntity);

	UserEntity checkUserExistsOrNot(UserEntity userEntity);

	public void removeUser(String id);
}
