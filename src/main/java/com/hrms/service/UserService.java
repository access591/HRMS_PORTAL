package com.hrms.service;

import java.util.List;

import com.hrms.model.Login;
import com.hrms.model.UserEntity;


public interface UserService {
	boolean checkUserExists(Login login);

	public List<UserEntity> getAllUsers();

	UserEntity findDataById(String id);

	void addUser(UserEntity userEntity);

	boolean checkUserExistsOrNot(UserEntity userEntity);

	public void removeUser(String id);
}
