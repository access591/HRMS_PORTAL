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
		try {
			return userProgramRightDao.findAll();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void addUserProgramRight(UserRights userRights) {
		userRights.setInsDate(new Date());
		 this.userProgramRightDao.saveOrUpdate(userRights);
		
	}
	@Override
	public boolean checkUserRightsExists(UserRights userRights) {
		UserRights e = userProgramRightDao.existOrNot(userRights);
		if (e != null) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public void removeUserProgramRight(long id) {
		this.userProgramRightDao.delete(id);
		
	}
	@Override
	public UserRights findUserRightById(long id) {
		return userProgramRightDao.findById(id);
	
	}
	@Override
	public void updateUserRights(UserRights ur) {
		ur.setUserCode(ur.getUserCode());
		ur.setModuleCode(ur.getModuleCode());
		ur.setSubModuleCode(ur.getSubModuleCode());
		ur.setPrgCode(ur.getPrgCode());
		ur.setUpdDate(ur.getUpdDate());
		this.userProgramRightDao.saveOrUpdate(ur);
		
	}

}
