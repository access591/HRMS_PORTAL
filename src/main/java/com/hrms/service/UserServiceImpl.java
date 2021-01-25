package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.model.Login;
import com.hrms.model.UserEntity;
import com.hrms.repository.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public boolean checkUserExists(Login login) {
		UserEntity e = userDao.findUser(login);
		if (e != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<UserEntity> getAllUsers() {
		List<UserEntity> listUsers = userDao.getAllUsers();
		return listUsers;
	}

	@Override
	public UserEntity findDataById(String id) {
		return userDao.findDataById(id);

	}

	@Override
	public void addUser(UserEntity userEntity) {
		this.userDao.addUser(userEntity);
		
	}

	@Override
	public boolean checkUserExistsOrNot(UserEntity userEntity) {
	
		UserEntity e = userDao.checkUserExistsOrNot(userEntity);
			if (e != null) {
				return true;
			} else {
				return false;
			}
	}

	@Override
	public void removeUser(String id) {
		this.userDao.removeUser(id);
		
	}
	}
