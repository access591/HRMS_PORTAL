package com.hrms.repository;

import java.util.List;

import com.hrms.model.UserRights;

public interface UserProgramRightDao {
	List<UserRights> getAllUserRights();
	public void addUserProgramRight(UserRights userRights);
	  UserRights checkUserRightsExists(UserRights userRights);

}
