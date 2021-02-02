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
	@Override
	public void removeUserProgramRight(long id) {
		this.userProgramRightDao.removeUserProgramRight(id);
		
	}
	@Override
	public UserRights findUserRightById(long id) {
		return userProgramRightDao.findUserRightById(id);
	
	}
	@Override
	public void updateUserRights(UserRights ur) {
		ur.setUser_code(ur.getUser_code());
		ur.setModule_code(ur.getModule_code());
		ur.setSub_module_code(ur.getSub_module_code());
		ur.setPrg_code(ur.getPrg_code());
		ur.setUpd_date(ur.getUpd_date());
		this.userProgramRightDao.updateUserRights(ur);
		
	}

}
