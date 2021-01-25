package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.UserRights;
import com.hrms.repository.UserProgramRightDao;
@Service
public class UserProgramRightServiceImpl implements UserProgramRightService {
	@Autowired
	UserProgramRightDao userProgramRightDao;
	@Override
	public List<UserRights> getAllUserRights() {
		
		List<UserRights> listUserRights = userProgramRightDao.getAllUserRights();
		return listUserRights;
	}

}
