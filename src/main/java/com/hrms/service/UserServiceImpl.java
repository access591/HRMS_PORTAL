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
}