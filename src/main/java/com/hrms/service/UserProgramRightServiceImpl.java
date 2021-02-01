package com.hrms.service;

import java.util.Date;
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
	@Override
	public void addUserProgramRight(UserRights userRights) {
		userRights.setIns_date(new Date());
		 this.userProgramRightDao.addUserProgramRight(userRights);
		
	}
	@Override
	public boolean checkUserRightsExists(UserRights userRights) {
		UserRights e = userProgramRightDao.checkUserRightsExists(userRights);
		if (e != null) {
			return true;
		} else {
			return false;
		}
	}

}
